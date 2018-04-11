package dsa;


public class PostfixEvaluator
{
  /** constant for addition symbol */
  private final char ADD = '+';
  /** constant for subtraction symbol */
  private final char SUBTRACT = '-';
  /** constant for multiplication symbol */
  private final char MULTIPLY = '*';
  /** constant for division symbol */
  private final char DIVIDE = '/';
  /** the stack */
  private GenericArrayStack<Integer> stack;

  /**
   * Sets up this evaluator by creating a new stack.
   */
  public PostfixEvaluator()
  {
    stack = new GenericArrayStack<>();
  }

  /**
   * Evaluates the specified postfix expression. If an operand is
   * encountered, it is pushed onto the stack. If an operator is
   * encountered, two operands are popped, the operation is
   * evaluated, and the result is pushed onto the stack.
   * @param expr String representation of a postfix expression
   * @return int value of the given expression
   */
  public int evaluate (String expr)
  {
    int op1, op2, result = 0;
    String[] tokens;
    tokens = expr.split("\\s");
    String token = "";
   // use token.charAt(0) if you need to access the first char
    for (int i=0; i<tokens.length;i++) {
    	if (isOperator(tokens[i])) {
    		op1 = stack.pop();
    		op2 = stack.pop();
    		token = tokens[i];
    		if (token.charAt(0) == ADD) {
    			result = evalSingleOp(ADD, op1, op2);
    			stack.push(result);
    		} else if (token.charAt(0) == SUBTRACT) {
    			result = evalSingleOp(SUBTRACT, op1, op2);
    			stack.push(result);
    		} else if (token.charAt(0) == MULTIPLY) {
    			result = evalSingleOp(MULTIPLY, op1, op2);
    			stack.push(result);
    		} else {
    			result = evalSingleOp(DIVIDE, op1, op2);
    			stack.push(result);
    		}
    	} else {
    		stack.push(Integer.parseInt(tokens[i]));
    	}
    }
   
    return result;
  }

  /**
   * Determines if the specified token is an operator.
   * @param token String representing a single token
   * @return boolean true if token is operator
   */
  private boolean isOperator (String token)
  {
    return ( token.equals("+") || token.equals("-") ||
             token.equals("*") || token.equals("/") );
  }

  /**
   * Peforms integer evaluation on a single expression consisting of 
   * the specified operator and operands.
   * @param operation operation to be performed
   * @param op1 the first operand
   * @param op2 the second operand
   * @return int value of the expression
   */
  private int evalSingleOp (char operation, int op1, int op2)
  {
    int result = 0;

    switch (operation)
    {
    	case '+':
    		result = op1 + op2;
    		break;
    	case '-':
    		result = op1-op2;
    		break;
    	case '*':
    		result = op1*op2;
    		break;
    	case '/':
    		result = op1/op2;
    		break;
    }

    return result;
  }
}