/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<fun def list> --> { <fun def> }+ 
<fun def> --> <header> <body>
<header> --> <fun name> "(" [ <parameter list> ] ")"
<fun name> --> <id>
<parameter list> --> <parameter> { "," <parameter> }
<parameter> --> <id>
<body> --> "{" <s list> "}"
<s list> --> { <statement> }
<statement> --> <assignment> | <cond> | <while> | <block> | <fun call statement> | <print>
<assignment> --> <var> "=" <right side> ";"
<var> --> <id var> | <array var> | "returnVal"
<id var> --> <id>
<array var> --> <array name> "[" <E list> "]"
<array name> --> <id>
<E list> --> <E> { "," <E> }
<right side> --> <array constructor> | <expr right side>
<array constructor> --> "new" "[" <E list> "]"
<expr right side> --> <expr>
<cond> --> "if" "(" <expr> ")" <statement> [ "else" <statement> ]
<while> --> "while" "(" <expr> ")" <statement>
<block> --> "{" <s list> "}"
<fun call statement> --> <fun call> ";"
<fun call> --> <fun name> "(" [ <expr list> ] ")"
<expr list> --> <expr> { "," <expr> }
<print> --> "print" <expr> ";"
<expr> --> <boolTerm> { || <boolTerm> }
<boolTerm> --> <boolPrimary> { && <boolPrimary> }
<boolPrimary> --> <E> [ <comp op> <E> ]
<comp op> --> "<" | "<=" | ">" | ">=" | "==" | "!="
<E> --> <term> { (+|-) <term> }
<term> --> <primary> { (*|/) <primary> }
<primary> --> <var primary> | <int> | <float> | <floatE> | "(" <expr> ")" | - <primary> | ! <primary> | <fun call primary>
<var primary> --> <var>
<fun call primary> --> <fun call>

NOTE: In the 2-branch conditionals, each "else" matches the closest preceding unmatched "if".
NOTE: The binary operators +, -, *, /, ||, && associate to left.

The definitions of the tokens are given in the lexical analyzer class file "LexAnalyzer.java". 

The following variables and functions of the "LexAnalyzer" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

The program will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks. 
The string variable "indent" will keep track of the correct number of blanks for indentation and
will be passed to parse functions corresponding to syntactic categories.

**/

import java.util.*;

public abstract class Parser extends LexAnalyzer
{
	static boolean syntaxErrorFound = false;
	static final ReturnVal returnVal_ = new ReturnVal();

	public static HashMap<String, FunDef> funDefMap = new HashMap<String, FunDef>();
		// Stores the FunDef objects indexed by function names;
		// used by Eval() to evaluate function-call expressions and by M() to interpret function-call statements.

	public static FunDefList funDefList()

	// <fun def list> --> { <fun def> }+ 

	{
		LinkedList<FunDef> funDefList = new LinkedList<FunDef>();

		FunDef funDef = funDef();
		funDefList.add(funDef);
		while ( state == State.Id ) // Parse <fun def> as long as the token is <fun name>.
		{
			funDef = funDef();
			funDefList.add(funDef);
			
		}
		return new FunDefList(funDefList);
	}

	public static FunDef funDef()

	// <fun def> --> <header> <body>

	{
		Header header = header();
		Body body = body();
		FunDef funDef = new FunDef(header, body);
		if ( header != null )
			funDefMap.put(header.funName.id.id, funDef);
		return funDef;
	}

	public static Header header()

	// <header> --> <fun name> "(" [ <parameter list> ] ")"

	{
		if ( state == State.Id ) // The token is <fun name>.
		{
			FunName funName = new FunName(new Id(t));
			getToken();
			if ( state == State.LParen )
			{
				getToken();
				if ( state == State.RParen ) // <parameter list> is non-existent.
				{
					getToken();
					return new HeaderWithoutParameters(funName);
				}
				else
				{
					ParameterList parameterList = parameterList();
					if ( state == State.RParen )
					{
						getToken();
						return new HeaderWithParameters(funName, parameterList);
					}
					else
						errorMsg(1);
				}
			}
			else
				errorMsg(2);
		}
		else
			errorMsg(3);
		return null;
	}

	public static ParameterList parameterList()

	// <parameter list> --> <parameter> { "," <parameter> }

	{
		LinkedList<Parameter> parameterList = new LinkedList<Parameter>();

		Parameter parameter = parameter();
		parameterList.add(parameter);
		while ( state == State.Comma )
		{
			getToken();
			parameter = parameter();
			parameterList.add(parameter);
		}
		return new ParameterList(parameterList);
	}

	public static Parameter parameter()

	// <parameter> --> <id>

	{
		if ( state == State.Id )
		{
			Parameter parameter = new Parameter(new Id(t));
			getToken();
			return parameter;
		}
		else
		{
			errorMsg(12);
			return null;
		}
	}

	public static Body body()
	
	// <body> --> "{" <s list> "}"
	
	{
		if ( state == State.LBrace )
		{
			getToken();
			SList sList = sList();
			if ( state == State.RBrace )
			{
				getToken();
				return new Body(sList);
			}
			else
			{
				errorMsg(9);
				return null;
			}
		}
		else
		{
			errorMsg(11);
			return null;
		}
	}

	public static SList sList()

	// <s list> --> { <statement> }

	{
		LinkedList<Statement> sList = new LinkedList<Statement>();

		while ( beginsStatement() )
		{
			Statement statement = statement();
			sList.add(statement);
			
		}
		return new SList(sList);
	}

	public static Statement statement()

	// <statement> --> <assignment> | <cond> | <while> | <block> | <fun call statement> | <print>

	{
		switch ( state )
		{
		case Id: // <assignment> or <fun call statement>
			Id id = new Id(t);
			getToken();
			if ( state == State.LParen ) // <fun call statement>
			{
				getToken();
				FunCallStatement funCallStatement = funCallStatement(id);
				return funCallStatement;
			}
			else if ( state == state.LBracket ) // <assignment> to <array var>
			{
				getToken();
				ArrayVar arrayVar = arrayVar(id);
				return assignment(arrayVar);
			}
			else // <assignment> to <id var>
				return assignment(new IdVar(id));

		case Keyword_returnVal: // <assignment> to returnVal
			getToken();
			return assignment(returnVal_);

		case Keyword_if:
			return cond();

		case Keyword_while:
			return while_();

		case LBrace:
		         return block();

		case Keyword_print:
			return print();

		default:
			errorMsg(4);
			return null;
		}
	}

	public static boolean beginsStatement()
	{
		return state == State.Id || state == State.Keyword_returnVal ||
		       state == State.Keyword_if || state == State.Keyword_while ||
		       state == State.LBrace || state == State.Keyword_print
		       ;
	}

	public static ArrayVar arrayVar(Id id) // id is the array name extracted.

	// <array var> --> <array name> "[" <E list> "]"
	// <array name > -- <id>

	{	// <array name> and "[" already extracted; start parsing <E list> "]".

		EList eList = eList();
		if ( state == State.RBracket )
		{
			getToken();
			ArrayName arrayName = new ArrayName(id);
			return new ArrayVar(arrayName, eList);
		}
		else
		{
			errorMsg(5);
			return null;
		}
	}

	public static EList eList()

	// <E list> --> <E> { "," <E> }

	{
		LinkedList<E> eList = new LinkedList<E>();

		E e = E();
		eList.add(e);
		while ( state == State.Comma )
		{
			getToken();
			e = E();
			eList.add(e);
		}
		return new EList(eList);
	}

	public static Assignment assignment(Var var) // The parameter var is the <var> on the left side.

	// <assignment> --> <var> "=" <right side> ";"

	{
		if ( state == State.Assign )
		{
			getToken();
			RightSide rightSide = rightSide();
			if ( state == State.Semicolon )
			{
				getToken();
				return new Assignment(var, rightSide);
			}
			else
			{
				errorMsg(6);
				return null;
			}
		}
		else
		{
			errorMsg(7);
			return null;
		}
	}

	public static Var var() // This function is not used by the parser.

	// <var> --> <id var> | <array var> | "returnVal"
	// <id var> --> <id>

	{
		switch ( state )
		{
		case Id:	
			Id id = new Id(t);
			getToken();
			if ( state == state.LBracket ) // <array var>
			{
				getToken();
				return arrayVar(id);
			}
			else // <id var>
				return new IdVar(id);

		case Keyword_returnVal:
			getToken();
			return returnVal_;

		default:
			errorMsg(13);
			return null;
		}
	}

	public static RightSide rightSide()

	// <right side> --> <array constructor> | <expr right side>
	// <expr right side> --> <expr>

	{
		if ( state == State.Keyword_new )
		{
			getToken();
			return arrayConstructor();
		}
		else
		{
			Expr expr = expr();
			return new ExprRightSide(expr);
		}
	}

	public static ArrayConstructor arrayConstructor()

	// <array constructor> --> "new" "[" <E list> "]"

	{	// "new" already extracted; start parsing "[" <E list> "]"

		if ( state == State.LBracket )
		{
			getToken();
			EList eList = eList();
			if ( state == State.RBracket )
			{
				getToken();
				return new ArrayConstructor(eList);
			}
			else
			{
				errorMsg(5);
				return null;
			}
		}
		else
		{
			errorMsg(8);
			return null;
		}
	}

	public static Cond cond()

	// <cond> --> "if" "(" <expr> ")" <statement> [ "else" <statement> ]

	{
		getToken(); // flush "if"
		if ( state == State.LParen )
		{
			getToken();
			Expr expr = expr();
			if ( state == State.RParen )
			{
				getToken();
				Statement statement1 = statement();
				if ( state == State.Keyword_else )
				{
					getToken();
					Statement statement2 = statement();
					return new If2(expr, statement1, statement2);
				}
				else
					return new If1(expr, statement1);
			}
			else
				errorMsg(1);
		}
		else
			errorMsg(2);
		return null;
	}

	public static While while_()

	// <while> --> "while" "(" <expr> ")" <statement>

	{
		getToken(); // flush "while"
		if ( state == State.LParen )
		{
			getToken();
			Expr expr = expr();
			if ( state == State.RParen )
			{
				getToken();
				Statement statement = statement();
				return new While(expr, statement);
			}
			else
				errorMsg(1);
		}
		else
			errorMsg(2);
		return null;
	}

	public static Block block()
	
	// <block> --> "{" <s list> "}"
	
	{
		getToken(); // flush "{"
		SList sList = sList();
		if ( state == State.RBrace )
		{
			getToken();
			return new Block(sList);
		}
		else
		{
			errorMsg(9);
			return null;
		}
	}

	public static FunCallStatement funCallStatement(Id id) // id is the function name extracted.

	// <fun call statement> --> <fun call> ";"

	{
		FunCall funCall = funCall(id);
		if ( state == State.Semicolon )
		{
			getToken();
			return new FunCallStatement(funCall);
		}
		else
		{
			errorMsg(6);
			return null;
		}
	}

	public static FunCall funCall(Id id) // id is the function name extracted.

	// <fun call> --> <fun name> "(" [ <expr list> ] ")"

	{	// <fun name> and "(" already extracted; start parsing <expr list> ")".

		if ( state == State.RParen ) // Parameters <expr list> is non-existent.
		{
			getToken();
			FunName funName = new FunName(id);
			return new FunCallWithoutParameters(funName);
		}
		else
		{
			ExprList exprList = exprList();
			if ( state == State.RParen )
			{
				getToken();
				FunName funName = new FunName(id);
				return new FunCallWithParameters(funName, exprList);
			}
			else
			{
				errorMsg(1);
				return null;
			}
		}
	}

	public static ExprList exprList()

	// <expr list> --> <expr> { "," <expr> }

	{
		LinkedList<Expr> exprList = new LinkedList<Expr>();

		Expr expr = expr();
		exprList.add(expr);
		while ( state == State.Comma )
		{
			getToken();
			expr = expr();
			exprList.add(expr);
		}
		return new ExprList(exprList);
	}

	public static Print print()

	// <print> --> "print" <expr> ";"

	{
		getToken(); // flush "print"
		Expr expr = expr();
		if ( state == State.Semicolon )
		{
			getToken();
			return new Print(expr);
		}
		else
		{
			errorMsg(6);
			return null;
		}
	}

	public static Expr expr()

	// <expr> --> <boolTerm> { "||" <boolTerm> }

	{
		LinkedList<BoolTermItem> boolTermItemList = new LinkedList<BoolTermItem>();

		BoolTerm boolTerm = boolTerm();
		boolTermItemList.add(new SingleBoolTermItem(boolTerm));
		while ( state == State.Or )
		{
			getToken();
			boolTerm = boolTerm();
			boolTermItemList.add(new OrBoolTermItem(boolTerm));
		}
		return new Expr(boolTermItemList);
	}

	public static BoolTerm boolTerm()

	// <boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }

	{
		LinkedList<BoolPrimaryItem> boolPrimaryItemList = new LinkedList<BoolPrimaryItem>();

		BoolPrimary boolPrimary = boolPrimary();
		boolPrimaryItemList.add(new SingleBoolPrimaryItem(boolPrimary));
		while ( state == State.And )
		{
			getToken();
			boolPrimary = boolPrimary();
			boolPrimaryItemList.add(new AndBoolPrimaryItem(boolPrimary));
		}
		return new BoolTerm(boolPrimaryItemList);
	}

	public static BoolPrimary boolPrimary()

	// <boolPrimary> --> <E> [ <comp op> <E> ]
	// <comp op> --> "<" | "<=" | ">" | ">=" | "==" | "!="

	{
		E e1 = E();
		if ( state.isCompOp() )
		{
			State compOp = state;
			getToken();
			E e2 = E();
			switch( compOp )
			{
			case Lt: return new Lt(e1, e2);
			case Le: return new Le(e1, e2);
			case Gt: return new Gt(e1, e2);
			case Ge: return new Ge(e1, e2);
			case Eq: return new Eq(e1, e2);
			default: return new Neq(e1, e2);
			}
		}
		else
			return new SingleE(e1);
	}

	public static E E()

	// <E> --> <term> { (+|-) <term> }

	{
		LinkedList<TermItem> termItemList = new LinkedList<TermItem>();

		Term term = term();
		termItemList.add(new SingleTermItem(term));
		while ( state == State.Add | state == State.Sub )
		{
			State op = state;
			getToken();
			term = term();
			if ( op == State.Add )
				termItemList.add(new AddTermItem(term));
			else // op == State.Sub
				termItemList.add(new SubTermItem(term));
		}
		return new E(termItemList);
	}

	public static Term term()

	// <term> --> <primary> { (*|/) <primary> }

	{
		LinkedList<PrimaryItem> primaryItemList = new LinkedList<PrimaryItem>();

		Primary primary = primary();
		primaryItemList.add(new SinglePrimaryItem(primary));
		while ( state == State.Mul | state == State.Div )
		{
			State op = state;
			getToken();
			primary = primary();
			if ( op == State.Mul )
				primaryItemList.add(new MulPrimaryItem(primary));
			else // op == State.Div
				primaryItemList.add(new DivPrimaryItem(primary));
		}
		return new Term(primaryItemList);
	}

	public static Primary primary()

	// <primary> --> <var primary> | <int> | <float> | <floatE> | "(" <expr> ")" | - <primary> | ! <primary> | <fun call primary>
	// <var primary> --> <var>
	
	{
		switch ( state )
		{
		case Id:	
			Id id = new Id(t);
			getToken();
			if ( state == State.LParen ) // <fun call primary>
			{
				getToken();
				FunCallPrimary funCallPrimary = funCallPrimary(id);
				return funCallPrimary;
			}
			else if ( state == state.LBracket ) // <array var>
			{
				getToken();
				ArrayVar arrayVar = arrayVar(id);
				return new VarPrimary(arrayVar);
			}
			else // <id var>
				return new VarPrimary(new IdVar(id));

		case Keyword_returnVal:
			getToken();
			return new VarPrimary(returnVal_);

		case Int:
			Int intElem = new Int(Integer.parseInt(t));
			getToken();
			return intElem;

		case Float: case FloatE:
			Floatp floatElem = new Floatp(Double.parseDouble(t));
			getToken();
			return floatElem;

		case LParen:
			getToken();
			Expr expr = expr();
			if ( state == State.RParen )
			{
				getToken();
				Parenthesized paren = new Parenthesized(expr);
				return paren;
			}
			else
			{
				errorMsg(1);
				return null;
			}

		case Sub:	
			getToken();
			Primary primary = primary();
			return new NegPrimary(primary);

		case Inv:	
			getToken();
			Primary primary_ = primary();
			return new InvPrimary(primary_);

		default:
			errorMsg(10);
			return null;
		}
	}

	public static FunCallPrimary funCallPrimary(Id id) // id is the function name extracted.

	// <fun call primary> --> <fun call>

	{
		FunCall funCall = funCall(id);
		return new FunCallPrimary(funCall);
	}

	public static void errorMsg(int i)
	{
		syntaxErrorFound = true;
		
		display(t + " : Syntax Error, unexpected symbol where");

		switch( i )
		{
		case 1:	 displayln(" ) expected"); return;
		case 2:	 displayln(" ( expected"); return;
		case 3:	 displayln(" function name expected"); return;
		case 4:  displayln(" id, returnVal, if, while, {, or print expected"); return;
		case 5:	 displayln(" ] expected"); return;
		case 6:	 displayln(" ; expected"); return;
		case 7:	 displayln(" = expected"); return;
		case 8:	 displayln(" [ expected"); return;
		case 9:	 displayln(" } expected"); return;
		case 10: displayln(" id, returnVal, int, float, (, -, or ! expected"); return;
		case 11: displayln(" { expected"); return;
		case 12: displayln(" function parameter expected"); return;
		case 13: displayln(" id or returnVal expected"); return;
		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing a string of <fun def list>
		// argv[1]: output file displaying the parse tree or error messages

		setIO( argv[0], argv[1] );
		setLex();

		getToken();
		FunDefList funDefList = funDefList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol");
		else if ( ! syntaxErrorFound )
			funDefList.printParseTree("");

		closeIO();
	}
}
