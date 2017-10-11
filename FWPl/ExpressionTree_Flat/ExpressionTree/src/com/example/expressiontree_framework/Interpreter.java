package com.example.expressiontree_framework;

import java.util.Stack;

/**
 * @class Interpreter
 *
 * @brief Parses incoming expression strings into a parse tree and
 *        builds an expression tree from the parse tree.  This class
 *        plays the role of the "Interpreter" in the Intepreter
 *        pattern, tweaked to use the precedence of operators/operands
 *        to guide the creation of the parse tree.  It also uses the
 *        Builder pattern to build the component nodes in the
 *        Composite-based expression tree.
 */
public class Interpreter {
    /** Stores numbers with multiple digits. */
     public int multiDigitNumbers;

    /** Stores the previous symbol. */
     public com.example.expressiontree_framework.Symbol lastValidInput;

    /** Tracks the precedence of the expression. */
     public int accumulatedPrecedence;

    /** The precedence of the '+' and '-' operators. */
    final static public int addSubPrecedence = 1;

    /** The precedence of the '*' and '/' operators. */
    final static public int mulDivPrecedence = 2;

    /** The precedence of a '-' (negate) operator. */
    final static public int negatePrecedence = 3;

    /** The precedence of a number. */
    final static public int numberPrecedence = 4;

    /** The precedence of a number. */
    final static public int parenPrecedence = 5;

    /** Factory that makes an expression tree. */
     public com.example.expressiontree_framework.ExpressionTreeFactory expressionTreeFactory;

    /**
     * Stores variables and their values for use by the Interpreter.
     */
     public com.example.expressiontree_framework.SymbolTable symbolTable =
        new com.example.expressiontree_framework.SymbolTable();

    /** 
     * Accessor method. 
     */
    public com.example.expressiontree_framework.SymbolTable symbolTable() {
        return symbolTable;
    }

    /**
     * Provide a default implementation of the @a
     * ExpressionTreeFactory.
     */
    public Interpreter() {
        this.expressionTreeFactory =
            new com.example.expressiontree_framework.ExpressionTreeFactory();
    }

    /**
     * Provide the means to override the default @a
     * ExpressionTreeFactory.
     */
    public Interpreter(com.example.expressiontree_framework.ExpressionTreeFactory expressionTreeFactory) {
        this.expressionTreeFactory =
            expressionTreeFactory;
    } public

    /**
     * This method first converts a string into a parse tree and then
     * build an expression tree out of the parse tree.  It's
     * implemented using the Template Method pattern.
     */
    com.example.expressiontree_framework.ExpressionTree interpret(String inputExpression) {
        /** The parseTree is implemented as a Stack. */
        Stack<com.example.expressiontree_framework.Symbol> parseTree =
            buildParseTree(inputExpression);

        /** 
         * If the parseTree has an element in it perform the
         * (optional) optimization pass and then build the
         * ExpressionTree fro the parseTree.
         */
        if (!parseTree.empty()) {
            /*
             * This hook method can be overridden to optimize the
             * parseTree prior to generating the ExpressionTree.
             */
            optimizeParseTree(parseTree);

            /* Build the ExpressionTree from the parseTree. */
            return buildExpressionTree(parseTree);
        } else
            /** If we reach this, we didn't have any symbols. */
            return expressionTreeFactory.makeExpressionTree(null);
    }

    public Stack<com.example.expressiontree_framework.Symbol> buildParseTree (String inputExpression) {
        Stack<com.example.expressiontree_framework.Symbol> parseTree = new Stack<com.example.expressiontree_framework.Symbol>();

        // Keep track of the last valid input, which is useful for
        // handling unary minus (negation) operators.
        lastValidInput = null;

        boolean handled = false;

        /** Initialize some data members to their default values. */
        accumulatedPrecedence = 0;
        multiDigitNumbers = 0;

        for(int index = 0;
            index < inputExpression.length();
            ++index) {
            /** 
             * Locate the next symbol in the input and place it
             * into the parse tree according to its precedence.
             */
            
                                        
                                        
                                        parseTree = parseNextSymbol(inputExpression, index, handled, parseTree);

            if(multiDigitNumbers > index)
                index = multiDigitNumbers;
        }

        return parseTree;
    }

    /**
     * This hook method can be overridden to conduct optimization on
     * the @a parseTree prior to generating the @a ExpressionTree.  By
     * default it's a no-op.
     */
    protected void optimizeParseTree(Stack<com.example.expressiontree_framework.Symbol> parseTree) {
    }

    /** 
     * Invoke a recursive build of the ExpressionTree, starting with
     * the root symbol, which should be the one and only item in the
     * linked list.  The Builder pattern is used at each node to
     * create the appropriate subclass of @a ComponentNode.
     */
    protected com.example.expressiontree_framework.ExpressionTree buildExpressionTree(Stack<com.example.expressiontree_framework.Symbol> parseTree) {
        /** there had better only be one element left in the stack! */
        assert (parseTree.size() == 1);
        return expressionTreeFactory.makeExpressionTree(parseTree.peek().build());
    }

    /** Parse next terminal expression. */
     public Stack<com.example.expressiontree_framework.Symbol> parseNextSymbol
        (String inputExpression,
         int index,
         boolean handled,
         Stack<com.example.expressiontree_framework.Symbol> parseTree) {
        handled = false;
        if(Character.isDigit(inputExpression.charAt(index))) {
            handled = true;
            
                                               
                                               
                                               parseTree = insertNumberOrVariable(inputExpression, index, parseTree, false);
        } else if(Character.isLetterOrDigit(inputExpression.charAt(index))) {
            handled = true;
             
                                               
                                               
                                               parseTree = insertNumberOrVariable(inputExpression, index, parseTree, true);
        } else if(inputExpression.charAt(index) == '+') {
            handled = true;
            /** Addition operation. */
            com.example.expressiontree_framework.Add op = new com.example.expressiontree_framework.Add();
            op.addPrecedence(accumulatedPrecedence);

            lastValidInput = null;

            /** 
             * Insert the op according to left-to-right
             * relationships. 
             */
             parseTree = insertSymbolByPrecedence(op, parseTree);
        } else if(inputExpression.charAt(index) == '-') {
            handled = true;

            com.example.expressiontree_framework.Symbol op = null;
            /** Subtraction operation. */
            com.example.expressiontree_framework.Number number = null;

            if (lastValidInput == null) {
                /** Negate. */
                op = new com.example.expressiontree_framework.Negate();
                op.addPrecedence(accumulatedPrecedence);
            } else {
                /** Subtract. */
                op = new com.example.expressiontree_framework.Subtract();
                op.addPrecedence(accumulatedPrecedence);
            }

            lastValidInput = null;

            /** 
             * Insert the op according to left-to-right
             * relationships.
             */
             parseTree = insertSymbolByPrecedence(op, parseTree);
        } else if(inputExpression.charAt(index) == '*') {
            handled = true;
            /** Multiplication operation. */
            com.example.expressiontree_framework.Multiply op = new com.example.expressiontree_framework.Multiply();
            op.addPrecedence(accumulatedPrecedence);

            lastValidInput = null;

            /** 
             * Insert the op according to precedence
             * relationships. 
             */
             parseTree = insertSymbolByPrecedence(op, parseTree);
        }
        else if(inputExpression.charAt(index) == '/') {
            handled = true;
            /** Division Operation. */
            com.example.expressiontree_framework.Divide op = new com.example.expressiontree_framework.Divide();
            op.addPrecedence(accumulatedPrecedence);

            lastValidInput = null;

            /** 
             * Insert the op according to precedence
             * relationships. 
             */
             parseTree = insertSymbolByPrecedence(op, parseTree);
        }
        else if(inputExpression.charAt(index) == '(') {
            handled = true;
            
                                          
                                          
                                          parseTree = handleParentheses(inputExpression, index, handled, parseTree);
        }
        else if(inputExpression.charAt(index)== ' ' 
                || inputExpression.charAt(index) == '\n') {
            handled = true;
            /** Skip whitespace. */
        }

        return parseTree;
    }

    /** Inserts a @a Number into the parse tree. */
     public Stack<com.example.expressiontree_framework.Symbol> insertNumberOrVariable(String input,
                                                 int startIndex,
                                                 Stack<com.example.expressiontree_framework.Symbol> parseTree,
                                                 boolean isVariable)  {
        /** 
         * Merge all consecutive number chars into a single Number
         * symbol, eg '123' = int (123). Scope of j needs to be
         * outside of the for loop.
         */
        int endIndex = 1;

        /** 
         * Explanation for this additional if statement: if input = 1,
         * an out of bounds exception is thrown as a result of the
         * charAt() statement.
         */
        if(input.length() > startIndex + endIndex)
            /** Locate the end of the number. */
            for(;
                startIndex + endIndex < input.length () 
                    && Character.isDigit(input.charAt(startIndex + endIndex)); 
                ++endIndex)
                continue;

        com.example.expressiontree_framework.Number number;

        if (isVariable)
            /** Lookup the value in the symbolTable. */
            
                                                           number =
                new com.example.expressiontree_framework.Number(symbolTable.get(input.substring(startIndex, startIndex 
                                                           + endIndex)));
        else
            
                                           number =
                new com.example.expressiontree_framework.Number(input.substring(startIndex, startIndex + endIndex));

        number.addPrecedence(accumulatedPrecedence);

        lastValidInput = number;

        /** 
         * Update startIndex to the last character that was a number.
         * The ++startIndex will update the startIndex at the end of
         * the loop to the next check.
         */
        startIndex += endIndex - 1;
        multiDigitNumbers = startIndex;

        return  insertSymbolByPrecedence(number, parseTree);
    }

    /** Inserts a @a Symbol into the parse tree. */
     public Stack<com.example.expressiontree_framework.Symbol> insertSymbolByPrecedence
        (com.example.expressiontree_framework.Symbol symbol,
         Stack<com.example.expressiontree_framework.Symbol> parseTree) {
        if(!parseTree.empty()) {
            /** 
             * If last element was a number, then make that our
             * left. 
             */
            com.example.expressiontree_framework.Symbol parent = parseTree.peek();
            com.example.expressiontree_framework.Symbol child = parent.right;

            if(child != null)
                /** 
                 * While there is a child of parent, keep going
                 * down the right side.
                 */
                for(;
                    child != null 
                        && child.precedence () < symbol.precedence ();
                    child = child.right)
                    parent = child;

            if(parent.precedence() < symbol.precedence()) {
                /** 
                 * symbol left will be the old child. new
                 * parent child will be the symbol.  To allow
                 * infinite negations, we have to check for
                 * unaryoperator.
                 */
                if(symbol.left == null)
                    symbol.left = child;

                parent.right = symbol;
            } else {
                /** 
                 * This can be one of two things, either we
                 * are the same precedence or we are less
                 * precedence than the parent.  This also
                 * means different things for unary ops.  The
                 * most recent unary op (negate) has a higher
                 * precedence.
                 */
                com.example.expressiontree_framework.UnaryOperator up = new com.example.expressiontree_framework.Negate();

                if(symbol.getClass() == up.getClass()) {
                    for(;
                        child != null 
                            && child.precedence() == symbol.precedence();
                        child = child.right)
                        parent = child;

                    parent.right = symbol;
                } else {
                    /**
                     * Everything else is evaluated the
                     * same. For instance, if this is 5 *
                     * 4 / 2, and we currently have Mult
                     * (5,4) in the parseTree, we need to
                     * make parent our left child.
                     */
                    symbol.left = parent;
                    parseTree.pop();
                    parseTree.push(symbol);
                    parent = child;
                }
            }
        } else
            parseTree.push(symbol);

        return parseTree;
    }

     public Stack<com.example.expressiontree_framework.Symbol> handleParentheses
        (String inputExpression,
         int index,
         boolean handled,
         Stack<com.example.expressiontree_framework.Symbol> masterParseTree) {
        /** 
         * Handling parentheses is a lot like handling the original
         * interpret() call.  The difference is that we have to worry
         * about how the calling function has its masterParseTree
         * setup.
         */
        accumulatedPrecedence += parenPrecedence;
        Stack<com.example.expressiontree_framework.Symbol> localParseTree =
            new Stack<com.example.expressiontree_framework.Symbol>();

        handled = false;
        for(++index; 
            index < inputExpression.length(); 
            ++index) {
            
                                             
                                             
                                             localParseTree = parseNextSymbol(inputExpression, index, handled, localParseTree);

            if(multiDigitNumbers > index)
                index = multiDigitNumbers;

            if (inputExpression.charAt(index) == ')') {
                handled = true;
                accumulatedPrecedence -= parenPrecedence;
                break;
            }
        }

        if(masterParseTree.size() > 0 
           && localParseTree.size() > 0) {
                com.example.expressiontree_framework.Symbol op = masterParseTree.peek();

                /** Is it a node with 2 children? */
                if(op != null)
                    
                                                 masterParseTree = 
                        insertSymbolByPrecedence(localParseTree.peek(), masterParseTree);
                else if(op == null)
                    /** Is it a unary node (like negate)? */
                    
                                                 masterParseTree = 
                        insertSymbolByPrecedence(localParseTree.peek(), masterParseTree);
                else {
                    /** 
                     * Is it a terminal node (Number)?  If so,
                     * there's an error.
                     */
                }
	    }
        else if (localParseTree.size() > 0)
            masterParseTree = localParseTree;

        return masterParseTree;
    }
}