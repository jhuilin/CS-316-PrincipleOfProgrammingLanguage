public enum State 
{ 
// final states    ordinal number  token accepted 

	Add,             // 0          +
	Sub,             // 1          -
	Mul,             // 2          *
	Div,	         // 3          /
	Or,              // 4          ||
	And,             // 5          &&
	Inv,             // 6          !
	Lt,              // 7          <
	Le,              // 8          <=
	Gt,              // 9          >
	Ge,              // 10         >=
	Eq,              // 11         ==
	Neq,             // 12         !=
	Assign,          // 13         = 
	Id,              // 14         identifiers
	Int,             // 15         integers
	Float,           // 16         floats without exponentiation part
	FloatE,          // 17         floats with exponentiation part
	LParen,          // 18         (
	RParen,          // 19         )
	LBrace,          // 20         {
	RBrace,          // 21         }
	LBracket,        // 22         [
	RBracket,        // 23         ]
	Semicolon,       // 24         ;
	Comma,           // 25         ,

// non-final states                string recognized    

	Start,           // 26      the empty string
	Bar,             // 27         |
	Ampersand,       // 28         &
	Period,          // 29        "."
	E,               // 30      float parts ending with E or e
	EPlusMinus,      // 31      float parts ending with + or - in exponentiation par

// keyword states

	Keyword_if,
	Keyword_else,
	Keyword_while,
	Keyword_returnVal,
	Keyword_new,
	Keyword_print,

	UNDEF;

	// By enumerating the final states first and then the non-final states,
	// test for a final state is done by testing if the state's ordinal number
	// is less than or equal to that of Comma.

	boolean isFinal()
	{
		return this.compareTo(Comma) <= 0;
	}

	boolean isArithOp()
	{
		return this.compareTo(Div) <= 0;
	}

	boolean isBoolOp()
	{
		return this.compareTo(Or) >= 0 && this.compareTo(Inv) <= 0;
	}

	boolean isCompOp()
	{
		return this.compareTo(Lt) >= 0 && this.compareTo(Neq) <= 0;
	}
}