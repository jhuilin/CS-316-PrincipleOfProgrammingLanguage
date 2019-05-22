import java.util.LinkedList;

public abstract class Parser extends LexArithArray{
	
	static boolean errorFound = false;
	
	//<fun def list> --> { <fun def>}+
	public static FunDefList funDefList() 
	{
		LinkedList<FunDef> list = new LinkedList<>();
		
		FunDef funDef = funDef();
		list.add(funDef);
		while ( state == State.Id )
        {
			funDef = funDef();
			list.add(funDef); 
        }
		return new FunDefList(list);
	}
	
	//<fun def> --> <header><body>
	private static FunDef funDef() 
	{
		Header header = header();
		Body body = body();
		return new FunDef(header, body);
	}

	//<header> --> <fun name> "(" [ <parameter list> ] ")"
	private static Header header() 
	{
		FunName name = funName();
		if(state == State.LParen) 
		{
			getToken();
			if(state == State.Id) 
			{
				ParameterList pList = parameterList();
				if(state == State.RParen) 
				{
					getToken();
					return new Header(name, pList);
				}
				else 
					errorMsg(19);
			}
			else if(state == State.RParen)
			{
				getToken();
				return new Header(name, null);
			}
			else 
				errorMsg(19);
		}
		else 
			errorMsg(15);
		return null;
	}
	
	//<fun name> --> <id>
	private static FunName funName() 
	{
		if(state == State.Id) 
		{
			String id = t;
			getToken();
			return new FunName(id);
		}
		else 
			errorMsg(11);
		return null;
	}
	
	//<fun name> --> <id>
	private static FunName funName(String id) 
	{
		return new FunName(id);
	}

	//<parameter list> --> <parameter> { "," <parameter> }
	private static ParameterList parameterList() 
	{
		LinkedList<Parameter> list = new LinkedList<>();
		Parameter p = parameter();
		list.add(p);
		
		while(state == State.Comma) 
		{
			getToken();
			p = parameter();
			list.add(p);
		}
		return new ParameterList(list);
	}

	//<parameter> --> <id>
	private static Parameter parameter() 
	{
		if(state == State.Id) 
		{
			String id = t;
			getToken();
			return new Parameter(id);
		}
		else 
			errorMsg(11);
		return null;
	}

	//<body> --> "{" <s list> "}"
	private static Body body() 
	{
		if(state == State.LBrace) 
		{
			getToken();
			SList list = sList();
			if(state == State.RBrace) 
			{
				getToken();
				return new Body(list);
			}
			else 
				errorMsg(18);
		}
		else 
			errorMsg(4);
		return null;
	}

	//<s list> --> { <statement> }
	private static SList sList() 
	{
		LinkedList<Statement> statementList = new LinkedList<>();
		while(state != State.RBrace) 
		{
			Statement statement = statement();
			statementList.add(statement);
		}
		return new SList(statementList);
	}

	//<statement> --> <assignment> | <cond> | <while> | <block> | <fun call statement> | <print>
	private static Statement statement() 
	{
		switch(state) {
			case Id:
			case Keyword_returnVal:
			{
				return assignmentFunCallStatement();
			}
			case Keyword_if:
			{
				return cond();
			}
			case Keyword_while:
			{
				return parseWhile();
			}
			case LBrace:
			{
				return block();
			}
			case Keyword_print:
			{
				return print();
			}
			default:
				errorMsg(7);
				return null;
		}
	}
	
	//<assignment> --> <var> "=" <right side> ";"
	private static Assignment assignment(String id) 
	{
		Var var = var(id);
		if(state == State.Assign) 
		{
			getToken();
			RightSide rs = rightSide();
			if(state == State.Semicolon) 
			{
				getToken();
				return new Assignment(var, rs);
			}
			else
				errorMsg(9);
		}
		else
			errorMsg(6);
		return null;
	}

	//<var> --> <id var> | <array var> | "returnVal"
	private static Var var(String id) 
	{
		if(state == State.Keyword_returnVal) 
			return returnVal();
		else
			return idOrArray(id);
	}
	
	//decide is it id var or array var
	private static Var idOrArray(String id) 
	{
		if(state != State.LBracket)
			return new IdVar(id);
		else
			return arrayVar(id);	
	}
	//<array var> --> <array name> "[" <E list> "]"
	private static ArrayVar arrayVar(String id) 
	{
		if(state == State.LBracket) 
		{
			ArrayName name = arrayName(id);
			getToken();
			EList List = eList();
			if(state == State.RBracket) 
			{
				getToken();
				return new ArrayVar(name, List);
			}
			else
				errorMsg(5);
		}
		else
			errorMsg(2);
		return null;
	}
	
	//<array name> --> <id>
	private static ArrayName arrayName(String id) 
	{
		return new ArrayName(id);
	}
	
	//<E list> --> <E> { "," <E>}
	private static EList eList() 
	{
		LinkedList<E> list = new LinkedList<>();
		E e = e();
		list.add(e);
		while(state == State.Comma) 
		{
			getToken();
			e = e();
			list.add(e);
		}
		return new EList(list);
	}


	//<right side> --> <array constructor> | <expr right side>
	private static RightSide rightSide() 
	{
		if(state == State.Keyword_new) 
		{
			return arrayConstructor();
		}
		else
			return exprRightSide();
	}
	
	//<expr right side> --> <expr>
	private static ExprRightSide exprRightSide() 
	{
		Expr ep = expr();
		return new ExprRightSide(ep);
	}
	
	//<cond> --> "if" "(" <expr> ")" <statement> [ "else" <statement> ]
	private static Statement cond() 
	{
		if(state == State.Keyword_if)
		{
			getToken();
			if(state == State.LParen) 
			{
				getToken();
				Expr ep = expr();
				if(state == State.RParen)
				{
					getToken();
					Statement s = statement();
					LinkedList<Statement> elseStatements = new LinkedList<>();
					while(state == State.Keyword_else) 
					{
						getToken();
						Statement statement = statement();
						elseStatements.add(statement);

					}
					return new Cond(ep, s, elseStatements);
				}
				else
					errorMsg(19);
			}
			else
				errorMsg(15);
		}
		else 
			errorMsg(8);
		return null;
	}
	
	//<while> --> "while" "(" <expr> ")" <statement>
	private static Statement parseWhile() 
	{
		if(state == State.Keyword_while) 
		{
			getToken();
			if(state == State.LParen) 
			{
				getToken();
				Expr ep = expr();
				if(state == State.RParen) 
				{
					getToken();
					Statement s = statement();
					return new While(ep, s);
				}
				else 
					errorMsg(19);
			}
			else 
				errorMsg(15);
		}else 
			errorMsg(1);
		return null;
	}
	
	//<block> --> "{" <s list> "}"
	private static Statement block() 
	{
		if(state == State.LBrace) 
		{
			getToken();
			SList list = sList();
			if(state == State.RBrace) 
			{
				getToken();
				return new Block(list);
			}
			else 
				errorMsg(18);
		}
		else 
			errorMsg(4);
		return null;
	}
	
	//<fun call statement> --> <fun call> ";"
	private static FunCallStatement funCallStatement(String id) 
	{
		FunCall fc = funCall(id);
		if(state == State.Semicolon) 
		{
			getToken();
			return new FunCallStatement(fc);
		}
		else
			errorMsg(9);
		return null;
	}
	
	//<fun call> --> <fun name> "(" [ <expr list> ] ")"
	private static FunCall funCall(String id) 
	{
		FunName name = funName(id);
		if(state == State.LParen) 
		{
			getToken();
			if(state == State.RParen) 
			{
				getToken();
				return new FunCall(name, null);
			}
			else 
			{
				ExprList exprList = exprList();
				if(state == State.RParen) 
				{
					getToken();
					return new FunCall(name, exprList);
				}
				else
					errorMsg(19);
			}
		}
		else
			errorMsg(15);
		return null;
	}
	
	//<expr list> --> <expr> { "," <expr> }
	private static ExprList exprList() 
	{
		LinkedList<Expr> exprList = new LinkedList<>();
		Expr ep = expr();
		exprList.add(ep);
		while(state == State.Comma) 
		{
			getToken();
			ep = expr();
			exprList.add(ep);
		}

		return new ExprList(exprList);
	}
	
	//<print> --> "print" <expr> ";"
	private static Statement print() 
	{
		if(state == State.Keyword_print) 
		{
			getToken();
			Expr ep = expr();
			if(state == State.Semicolon) 
			{
				getToken();
				return new Print(ep);
			}
			else 
				errorMsg(9);
		}
		else 
			errorMsg(10);
		return null;
	}
	
	//<expr> --> <boolTerm> { "||" <boolTerm> }
	private static Expr expr() 
	{
		LinkedList<BoolTerm> list = new LinkedList<>();
		BoolTerm bt = boolTerm();
		list.add(bt);
		while(state == State.Or) 
		{
			getToken();
			bt = boolTerm();
			list.add(bt);
		}
		return new Expr(list);
	}
	//<boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }
	private static BoolTerm boolTerm() 
	{
		LinkedList<BoolPrimary> list = new LinkedList<>();
		BoolPrimary bp = boolPrimary();
		list.add(bp);
		while(state == State.And) 
		{
			getToken();
			bp = boolPrimary();
			list.add(bp);
		}
		return new BoolTerm(list);
	}
	
	//<boolPrimary> --> <E> [ <comp op> <E> ]
	private static BoolPrimary boolPrimary() 
	{
		E first = e();
		LinkedList<CompOp> compOp = new LinkedList<>();
		LinkedList<E> next = new LinkedList<>();
		while(state == State.Lt || state == State.Le || state == State.Gt || state == State.Ge || state == State.Eq || state == State.Neq) {
			CompOp c = compOp();
			compOp.add(c);
			E n = e();
			next.add(n);
		}
		return new BoolPrimary(first, compOp, next);
	}

	//<comp op> --> "<" | "<=" | ">" | ">=" | "==" | "!="
	private static CompOp compOp() 
	{
		if(state == State.Lt || state == State.Le || state == State.Gt || state == State.Ge || state == State.Eq || state == State.Neq) 
		{
			String op = t;
			getToken();
			return new CompOp(op);
		}
		else
			errorMsg(14);
		return null;
	}
	
	//<E> --> "<Term>"{(+|-)<Term>}
	private static E e() 
	{
		LinkedList<TermItem> list = new LinkedList<TermItem>();

        Term t = term();
        list.add(new SingleTermItem(t));
        while ( state == State.Sub || state == State.Add )
        {
            State operator = state;
            getToken();
            t = term();
            if ( operator == State.Add )
                list.add(new AddTermItem(t));
            else 
                list.add(new SubTermItem(t));
        }
        return new E(list);
	}
	
	//<Term>--><Primary>{(*|/)<primary>}
	private static Term term() 
	{
		LinkedList<Primary> list = new LinkedList<Primary>();

        Primary p = primary();
        list.add(new SinglePrimaryItem(p));
        while ( state == State.Mul || state == State.Div )
        {
            State op = state;
            getToken();
            p = primary();
            if ( op == State.Mul )
                list.add(new MulPrimaryItem(p));
            else 
                list.add(new DivPrimaryItem(p));
        }
        return new Term(list);
	}
	
	//<array constructor> --> "new" "[" <E list> "]"
	private static ArrayConstructor arrayConstructor() 
	{
		if(state == State.Keyword_new) 
		{
			getToken();
			if(state == State.LBracket) 
			{
				getToken();
				EList list = eList();
				if(state == State.RBracket) 
				{
					getToken();
					return new ArrayConstructor(list);
				}
				else 
					errorMsg(5);
			}
			else 
				errorMsg(2);
		}
		else 
			errorMsg(16);
		return null;
	}
	

	private static Statement assignmentFunCallStatement() 
	{
		String id = t;
		getToken();
		if(state == State.Assign || state == State.LBracket || state == State.Keyword_returnVal) 
		{
			Assignment assignment = assignment(id);
			return assignment;
		}
		else if(state == State.LParen) 
		{
			FunCallStatement s = funCallStatement(id);
			return s;
		}
		else 
			errorMsg(20);
		return null;
	}


	// "returnVal"
	private static ReturnVal returnVal() 
	{
		if(state == State.Keyword_returnVal) 
		{
			getToken();
			return new ReturnVal();
		}
		else
			errorMsg(13);
		return null;
	}

	private static Primary primary() 
	{
		switch(state)
		{
			case Id:
			{
				return varPrimaryFunCallPrimary();
			}
			case Keyword_returnVal:
			{
				String returnVal = t;
				getToken();
				return varPrimary(returnVal);
			}
			case Int:
			{
				int i = Integer.parseInt(t);
				getToken();
				return new IntPrimary(i);
			}
			case Float:
			{
				float f = Float.parseFloat(t);
				getToken();
				return new Floatp(f);
			}
			case FloatE:
			{
				float f = Float.parseFloat(t);
				getToken();
				return new FloatE(f);
			}
			case LParen:
			{
				getToken();
				Expr ex = expr();
				if(state == State.RParen)
				{
					getToken();
					return new Parenthesized(ex);
				}
				else
					errorMsg(19);
			}
			case Sub:
			{
				getToken();
				Primary p = primary();
				return new SubPrimary(p);
			}
			case Inv:
			{
				getToken();
				Primary p = primary();
				return new InvPrimary(p);
			}
			default:
				errorMsg(17);
		}
		return null;
	}

	private static Primary varPrimary(String id) 
	{
		Var v = var(id);
		return new VarPrimary(v);
	}

	private static Primary varPrimaryFunCallPrimary() 
	{
		String id = t;
		getToken();
		if(state == State.LParen)
		{
			FunCall fc = funCall(id);
			return new FunCallPrimary(fc);
		}
		else
			return varPrimary(id);
	}

	public static void errorMsg(int i)
    {
        errorFound = true;

        display(t + " : Syntax Error, unexpected symbol where");

        switch( i )
        {
            case 1:	displayln(" = expected"); return;
            case 2: displayln(" ) expected"); return;
            case 3:	displayln(" ( expected"); return;
            case 4:	displayln(" } expected"); return;
            case 5:	displayln(" if expected"); return;
            case 6: displayln(" returnVal expected"); return;
            case 7: displayln(" >, >=, <, <=, != or == expected"); return;
            case 8: displayln(" ; expected"); return;
            case 9: displayln(" new expected"); return;
            case 10: displayln(" Int, Float, Id, returnVal, (, ! or - expected"); return;
            case 11: displayln(" { expected"); return;
            case 12: displayln("  operators or ) expected"); return;
            case 13: displayln(" ( or = expected"); return;
            case 14: displayln(" while expected"); return;
            case 15: displayln(" print expected"); return;
            case 16: displayln(" if, id, while, print or { expected"); return;
            case 17: displayln(" id expected"); return;
            case 18: displayln(" [ expected"); return;
            case 19: displayln(" id or returnVal expected"); return;
            case 20: displayln(" ] expected"); return;
        }
    }
	
	public static void main(String argv[])
    {
        // argv[0]: input file containing an assignment list
        // argv[1]: output file displaying the parse tree

        setIO( argv[0], argv[1] );
        setLex();

        getToken();

        FunDefList assignmentList = funDefList(); // build a parse tree
        if ( ! t.isEmpty() )
            errorMsg(5);
        else if ( ! errorFound )
            assignmentList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file

        closeIO();
    }
}