grammar Test;

/*
 * Parser Rules
 */

query               : linetype+ EOF ;

quotes              : '\'' ;

leftparent          : '(' ;

rightparent         : ')' ;

equals              : '=' ;

endquery            : ';' ;

forslash            : '/' ;

operator            : ',' | '-' | '<' | '>' | '*' | '.' | '$' | '[' | ']' | '"' | '+' ;

digit               : DIGIT ;

hexidecimal         : HEXADECIMAL ;

utf                 : UTF ;

variable            : WORD ;

or                  : OR ;

iff                 : IF ;

then                : THEN ;

els                 : ELSE ;

quoted              : ('\''WORD'\'' | '\''DIGIT'\'' | '\'''\'' | '\''(WORD | DIGIT)) ;

command             : (SELECT | FROM | GROUP | WHERE | HAVING | UNION | ALL | ORDER | BY) ;

groupby             : command request command request;

orderby             : ORDER WHITESPACE BY WHITESPACE request NEWLINE? NEWLINE? ;

unionsetup          : (command request command NEWLINE?)
                    | UNION;

divsetup            : command WHITESPACE? digit forslash digit ;

altfunction         : variable leftparent (hexidecimal | utf) rightparent
                    | variable leftparent variable leftparent (hexidecimal | utf) rightparent rightparent ;

function            : variable leftparent (variable | digit | operator ) rightparent
                    | variable leftparent variable leftparent (variable | digit | operator ) rightparent rightparent;

plusdigit           : digit WHITESPACE? operator WHITESPACE? digit WHITESPACE? operator? WHITESPACE? digit? WHITESPACE? operator? WHITESPACE? digit?
                      WHITESPACE? operator? WHITESPACE? digit? WHITESPACE? operator? WHITESPACE? digit? WHITESPACE? operator? WHITESPACE? digit?;

isequal             : (variable WHITESPACE? equals WHITESPACE? variable) | ((digit | plusdigit) WHITESPACE? equals WHITESPACE? digit)
                    | (variable WHITESPACE? equals WHITESPACE? digit) | (variable WHITESPACE? equals WHITESPACE? quoted)
                    | (quoted WHITESPACE? equals WHITESPACE? quoted) | (variable WHITESPACE? equals WHITESPACE? request) ;

altencoding         : (WHITESPACE? variable WHITESPACE? equals WHITESPACE? (hexidecimal | utf))
                    | (WHITESPACE? variable WHITESPACE? equals WHITESPACE? altfunction )
                    | endquery WHITESPACE? altfunction request? ;

piggyback           : (endquery request endquery? quotes endquery?) ;

tautology           : or WHITESPACE? isequal ;

unionattack         : WHITESPACE? isequal WHITESPACE? unionsetup request command? request? ;

inference           : endquery? WHITESPACE? (iff | then) WHITESPACE? request? divsetup NEWLINE? (els | then) WHITESPACE? command request ;

request             : (operator | quotes | variable | digit | plusdigit | hexidecimal | altencoding | function
                    | isequal | tautology | piggyback | equals | unionattack | inference | unionsetup | endquery | rightparent | WHITESPACE)+ ;

nested              : command request NEWLINE command request NEWLINE command request equals WHITESPACE leftparent command request NEWLINE command request NEWLINE command request equals? WHITESPACE? leftparent? command? request? NEWLINE? command? request? NEWLINE? NEWLINE?
                    | command request NEWLINE command request NEWLINE command request leftparent command request NEWLINE command request NEWLINE? NEWLINE?;

longline            : command request NEWLINE command request NEWLINE command request NEWLINE groupby NEWLINE command request NEWLINE NEWLINE?
                    | command request NEWLINE command request NEWLINE command request NEWLINE orderby NEWLINE NEWLINE?;

line                : command request NEWLINE command request NEWLINE command request NEWLINE? NEWLINE?
                    | command request NEWLINE command request NEWLINE NEWLINE? NEWLINE? ;

union               : command request NEWLINE (command request NEWLINE) unionsetup NEWLINE command request NEWLINE command request NEWLINE command request NEWLINE? NEWLINE?
                    | command request NEWLINE (command request NEWLINE) (command request NEWLINE) unionsetup NEWLINE command request NEWLINE command request NEWLINE? NEWLINE?
                    | command request NEWLINE (command request NEWLINE) (command request NEWLINE) unionsetup NEWLINE command request NEWLINE command request NEWLINE command request NEWLINE? NEWLINE?
                    | command request NEWLINE (command request NEWLINE) (command request NEWLINE) unionsetup NEWLINE command request NEWLINE command request NEWLINE command request NEWLINE orderby NEWLINE? NEWLINE? ;

linetype            : line | nested | longline | union ;

/*
 * Lexer Rules
 */

fragment A          : ('A'|'a') ;
fragment B          : ('B'|'b') ;
fragment C          : ('C'|'c') ;
fragment D          : ('D'|'d') ;
fragment E          : ('E'|'e') ;
fragment F          : ('F'|'f') ;
fragment G          : ('G'|'g') ;
fragment H          : ('H'|'h') ;
fragment I          : ('I'|'i') ;
fragment J          : ('J'|'j') ;
fragment K          : ('K'|'k') ;
fragment L          : ('L'|'l') ;
fragment M          : ('M'|'m') ;
fragment N          : ('N'|'n') ;
fragment O          : ('O'|'o') ;
fragment P          : ('P'|'p') ;
fragment Q          : ('Q'|'q') ;
fragment R          : ('R'|'r') ;
fragment S          : ('S'|'s') ;
fragment T          : ('T'|'t') ;
fragment U          : ('U'|'u') ;
fragment V          : ('V'|'v') ;
fragment W          : ('W'|'w') ;
fragment X          : ('X'|'x') ;
fragment Y          : ('Y'|'y') ;
fragment Z          : ('Z'|'z') ;

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;

ALL                 : A L L ;
BY                  : B Y ;
SELECT              : S E L E C T ;
FROM                : F R O M ;
GROUP               : G R O U P ;
WHERE               : W H E R E ;
HAVING              : H A V I N G ;
UNION               : U N I O N ;
ORDER               : O R D E R ;
OR                  : O R ;
IF                  : I F ;
THEN                : T H E N ;
ELSE                : E L S E ;

WORD                : (LOWERCASE | UPPERCASE | '_')+ ;
DIGIT               : [0-9] ;
HEXADECIMAL         : '0x' ([a-fA-F0-9])+ ;
UTF                 : ('U+' | 'u+') ([a-fA-F0-9])+ ;
WHITESPACE          : (' ' | '\t') ;
NEWLINE             : ('\r'? '\n' | '\r')+ ;