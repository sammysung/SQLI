grammar Test;

/*
 * Parser Rules
 */

query               : linetype+ EOF ;

nested              : command request NEWLINE command request NEWLINE command request equals WHITESPACE leftparent command request NEWLINE command request NEWLINE command request equals? WHITESPACE? leftparent? command? request? NEWLINE? command? request? NEWLINE? NEWLINE?
                    | command request NEWLINE command request NEWLINE command request leftparent command request NEWLINE command request NEWLINE? NEWLINE?;

longline            : command request NEWLINE command request NEWLINE command request NEWLINE groupby NEWLINE command request NEWLINE NEWLINE?
                    | command request NEWLINE command request NEWLINE command request NEWLINE orderby NEWLINE NEWLINE?;

line                : command request NEWLINE command request NEWLINE command request NEWLINE? NEWLINE?
                    | command request NEWLINE command request NEWLINE NEWLINE? NEWLINE? ;

union               : command request NEWLINE (command request NEWLINE) (command request NEWLINE) unionsetup NEWLINE command request NEWLINE command request NEWLINE? NEWLINE?
                    | command request NEWLINE (command request NEWLINE) (command request NEWLINE) unionsetup NEWLINE command request NEWLINE command request NEWLINE command request NEWLINE? NEWLINE?
                    | command request NEWLINE (command request NEWLINE) (command request NEWLINE) unionsetup NEWLINE command request NEWLINE command request NEWLINE command request NEWLINE orderby NEWLINE? NEWLINE? ;

linetype            : line | nested | longline | union ;

request             : (operator | quotes | variable | digit | function | tautology | piggyback | storedprocedure | unionattack | unionsetup | endquery /*| leftparent*/ | rightparent | WHITESPACE)+ ;

tautology           : (variable WHITESPACE? equals WHITESPACE? variable) | (digit WHITESPACE? equals WHITESPACE? digit)
                    | (variable WHITESPACE? equals WHITESPACE? digit) | (variable WHITESPACE? equals WHITESPACE? quoted)
                    | (quoted WHITESPACE? equals WHITESPACE? quoted) | (variable WHITESPACE? equals WHITESPACE? request) ;

piggyback           : endquery request quotes ;

storedprocedure     : endquery procedures endquery request quotes endquery ;

unionattack         : WHITESPACE tautology request unionsetup request ;

command             : (SELECT | FROM | GROUP | WHERE | HAVING | UNION | ALL | ORDER | BY) ;

procedures          : SHUTDOWN ;

unionsetup          : command request command
                    | UNION;

groupby             : command request command request;

function            : variable leftparent (variable | digit | operator) rightparent;

orderby             : ORDER WHITESPACE BY WHITESPACE request NEWLINE? NEWLINE? ; /*: command request command request endquery
                    | ORDER BY request? ;*/

operator            : ',' | '-' | '<' | '>' | '*' | '.' | '$' | '[' | ']' | '"' ;

quotes              : '\'' ;

leftparent          : '(' ;

rightparent         : ')' ;

equals              : '=' ;

endquery            : ';' ;

digit               : DIGIT ;

variable            : WORD ;

quoted              : ('\''WORD'\'' | '\''DIGIT'\'' | '\'''\'' | '\''(WORD | DIGIT)) ;

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
fragment L          : ('L'|'l') ;
fragment M          : ('M'|'m') ;
fragment N          : ('N'|'n') ;
fragment O          : ('O'|'o') ;
fragment P          : ('P'|'p') ;
fragment R          : ('R'|'r') ;
fragment S          : ('S'|'s') ;
fragment T          : ('T'|'t') ;
fragment U          : ('U'|'u') ;
fragment V          : ('V'|'v') ;
fragment W          : ('W'|'w') ;
fragment Y          : ('Y'|'y') ;


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
SHUTDOWN            : S H U T D O W N ;
ORDER               : O R D E R ;

WORD                : (LOWERCASE | UPPERCASE | '_')+ ;

DIGIT               : [0-9] ;

WHITESPACE          : (' ' | '\t') ;

NEWLINE             : ('\r'? '\n' | '\r')+ ;