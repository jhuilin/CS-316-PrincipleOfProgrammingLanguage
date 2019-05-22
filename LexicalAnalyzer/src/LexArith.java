/**
 
This class is a lexical analyzer for the tokens defined by the grammar:

<Add> --> +
<Sub> --> -
<Mul> --> *
<Div> --> /
<LParen> --> "("
<RParen> --> ")"
<int> --> { <digit> }+
<id> --> <letter> { <letter> | <digit> }
<float> --> { <digit> }+ "." { <digit> }+
<floatE> --> <float> (E|e) [+|-] { <digit> }+
<or>      "||" 
<and>      "&&" 
<inv>      ! 
<lt>      "<" 
<le>      "<=" 
<gt>      ">" 
<ge>      ">=" 
<eq>      "==" 
<neq>      "!=" 
<assign>      "="
<LBrace>      "{" 
<RBrace>      "}" 
<LBracket>      "[" 
<RBracket>      "]" 
<semicolon>      ";" 
<comma>     	 ","  

This class implements a DFA that will accept the above tokens.

The DFA states are represented by the Enum type "State".
The DFA has the following 10 final states represented by enum-type literals:

state     token accepted

Id        identifiers
Int       integers
Float     floats without exponentiation part
FloatE    floats with exponentiation part
Add      +
Sub     -
Mul     *
Div       /
LParen    (
RParen    )
or>      "||" 
and>      "&&" 
inv      ! 
lt      "<" 
le      "<=" 
gt      ">" 
ge      ">=" 
eq      "==" 
neq      "!=" 
assign      "="
LBrace      "{" 
RBrace      "}" 
LBracket   "[" 
RBracket   "]" 
semicolon  ";" 
comma      ","


The DFA also uses the following 5 non-final states:

state      string recognized

Start      the empty string
Period     float parts ending with "."
E          float parts ending with E or e
EPlusMinus float parts ending with + or - in exponentiation part
keyword		represent if,else,new,print,while

The function "driver" operates the DFA. 
The function "nextState" returns the next state given the current state and the input character.

To recognize a different token set, modify the following:

  enum type "State" and function "isFinal"
  function "nextState" 

The functions "driver", "getToken" remain the same.

**/


public class LexArith extends IO
{
	public enum State 
       	{ 
	  // non-final states     ordinal number

		Start,				// 0
		Period,				// 1
		E,					// 2
		EPlusMinus,			// 3
		Ampersand,			// 4
		Bar,				// 5


	  // final states
		Id,					// 6
		Int,				// 7
		Float,				// 8
		FloatE,				// 9
		Add,              	// 10
		Sub,              	// 11
		Mul,              	// 12
		Div,               	// 13
		LParen,            	// 14
		RParen,				// 15	
		LBrace,            	// 16
		RBrace,			   	// 17
		LBracket,			// 18
		RBracket,			// 19
		Semicolon,			// 20
		Comma,				// 21
		Assign,				// 22
		Inv,				// 23
		Lt,					// 24
		Gt,					// 25
		Or,					// 26
		And,				// 27
		Le,					// 28
		Ge,					// 29
		Eq,					// 30
		Neq,				// 31
		Keyword_if,			// 32
		Keyword_while,		// 33
		Keyword_returnVal,	// 34
		Keyword_print,		// 35
		Keyword_new,		// 36
		Keyword_else,		// 37
		
		UNDEF;				// 38

		private boolean isFinal()
		{
			return ( this.compareTo(State.Id) >= 0 );  
		}	
	}

	// By enumerating the non-final states first and then the final states,
	// test for a final state can be done by testing if the state's ordinal number
	// is greater than or equal to that of Id.

	// The following variables of "IO" class are used:

	//   static int a; the current input character
	//   static char c; used to convert the variable "a" to the char type whenever necessary

	public static String t; // holds an extracted token
	public static State state; // the current state of the FA

	private static int driver()

	// This is the driver of the FA. 
	// If a valid token is found, assigns it to "t" and returns 1.
	// If an invalid token is found, assigns it to "t" and returns 0.
	// If end-of-stream is reached without finding any non-whitespace character, returns -1.

	{
		State nextSt; // the next state of the FA

		t = "";
		state = State.Start;

		if ( Character.isWhitespace((char) a) )
			a = getChar(); // get the next non-whitespace character
		if ( a == -1 ) // end-of-stream is reached
			return -1;

		while ( a != -1 ) // do the body if "a" is not end-of-stream
		{
			c = (char) a;
			nextSt = nextState( state, c, t );
			if ( nextSt == State.UNDEF ) // The FA will halt.
			{
				if ( state.isFinal() )
					return 1; // valid token extracted
				else // "c" is an unexpected character
				{
					t = t+c;
					a = getNextChar();
					return 0; // invalid token found
				}
			}
			else // The FA will go on.
			{
				state = nextSt;
				t = t+c;
				a = getNextChar();
			}
		}

		// end-of-stream is reached while a token is being extracted

		if ( state.isFinal() )
			return 1; // valid token extracted
		else
			return 0; // invalid token found
	} // end driver

	public static void getToken()

	// Extract the next token using the driver of the FA.
	// If an invalid token is found, issue an error message.
	
	{
		int i = driver();
		if ( i == 0 )
			displayln(t + " : Lexical Error, invalid token");
	}
	private static State nextState(State s, char c, String str)

	// Returns the next state of the FA given the current state and input char;
	// if the next state is undefined, UNDEF is returned.

	{
		switch( state )
		{
		case Start:
			if ( Character.isLetter(c) )
				return State.Id;
			else if ( Character.isDigit(c) )
				return State.Int;
			else if ( c == '+' )
				return State.Add;
			else if ( c == '-' )
				return State.Sub;
			else if ( c == '*' )
				return State.Mul;
			else if ( c == '/' )
				return State.Div;
			else if ( c == '(' )
				return State.LParen;
			else if ( c == ')' )
				return State.RParen;
			else if(c == '[')
				return State.LBracket;
			else if(c == ']')
				return State.RBracket;
			else if(c == '{')
				return State.LBrace;
			else if(c == '}')
				return State.RBrace;
			else if(c == ';')
				return State.Semicolon;
			else if(c == ',')
				return State.Comma;
			else if(c == '&')
				return State.Ampersand;
			else if(c == '|')
				return State.Bar;
			else if(c == '!')
				return State.Inv;
			else if(c == '<')
				return State.Lt;
			else if(c == '>')
				return State.Gt;
			else if(c == '=')
				return State.Assign;
			else
				return State.UNDEF;
		case Id:
			if(str.equals("i") && c == 'f')
				return State.Keyword_if;			
			else if(str.equals("els") && c == 'e')
				return State.Keyword_else;			
			else if(str.equals("whil") && c == 'e')
				return State.Keyword_while;			
			else if(str.equals("ne") && c == 'w')
				return State.Keyword_new;		
			else if(str.equals("prin") && c == 't')
				return State.Keyword_print;
			else if(str.equals("returnVa") && c == 'l')
				return State.Keyword_returnVal;
			else if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		case Int:
			if ( Character.isDigit(c) )
				return State.Int;
			else if ( c == '.' )
				return State.Period;
			else
				return State.UNDEF;
		case Period:
			if ( Character.isDigit(c) )
				return State.Float;
			else
				return State.UNDEF;
		case Float:
			if ( Character.isDigit(c) )
				return State.Float;
			else if ( c == 'e' || c == 'E' )
				return State.E;
			else
				return State.UNDEF;
		case E:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else if ( c == '+' || c == '-' )
				return State.EPlusMinus;
			else
				return State.UNDEF;
		case EPlusMinus:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		case FloatE:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		case Bar:
			if(c == '|')
				return State.Or;
			else
				return State.UNDEF;
		case Ampersand:
			if(c == '&')
				return State.And;
			else
				return State.UNDEF;
		case Inv:
			if(c == '=')
				return State.Neq;
			else
				return State.UNDEF;
		case Lt:
			if(c == '=')
				return State.Le;
			else
				return State.UNDEF;
		case Gt:
			if(c == '=')
				return State.Ge;
			else
				return State.UNDEF;
		case Assign:
			if(c == '=')
				return State.Eq;
			else
				return State.UNDEF;
		case Keyword_if:
		case Keyword_else:
		case Keyword_while:
		case Keyword_new:
		case Keyword_print:
		case Keyword_returnVal:
			if(Character.isLetterOrDigit(c))		//if the next char is letter or digit, return State.id
				return State.Id;
			else
				return State.UNDEF;					//else it is one of keywords
		default:
			return State.UNDEF;
		}
	} // end nextState

	public static void main(String argv[])

	{		
		// argv[0]: input file containing source code using tokens defined above
		// argv[1]: output file displaying a list of the tokens 

		setIO( argv[0], argv[1] );
		
		int i;

		while ( a != -1 ) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if ( i == 1 )
				displayln( t+"   : "+state.toString() );
			else if ( i == 0 )
				displayln( t+" : Lexical Error, invalid token");
		} 

		closeIO();
	}
} 
