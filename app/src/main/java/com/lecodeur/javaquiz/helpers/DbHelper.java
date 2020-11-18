package com.lecodeur.javaquiz.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lecodeur.javaquiz.R;
import com.lecodeur.javaquiz.model.History;
import com.lecodeur.javaquiz.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azeem.
 */
public class DbHelper extends SQLiteOpenHelper {

    //DB version, table and database name
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "javQuizDb";
    private static final String DB_TABLE_VARIABLES = "Variables";
    private static final String DB_TABLE_LOOPS = "Loops";
    private static final String DB_TABLE_ERRORS = "Errors";
    private static final String DB_TABLE_HISTORY = "History";


    //table column names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "optA";
    private static final String KEY_OPTB = "optB";
    private static final String KEY_OPTC = "optC";
    private static final String KEY_OPTD = "optD";


    private static final String KEY_NANME = "Name";
    private static final String KEY_DATE = "Date";
    private static final String KEY_SCORE = "Score";

    private SQLiteDatabase dbase;
    private int rowCountVariables = 0;
    private int rowCountLoops = 0;
    private int rowCountErrors = 0;
    private Context context;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sqlQueryVariables = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT ,%s TEXT)", DB_TABLE_VARIABLES, KEY_ID, KEY_QUES, KEY_ANSWER, KEY_OPTA, KEY_OPTB, KEY_OPTC, KEY_OPTD);
        String sqlQueryLoops = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT ,%s TEXT)", DB_TABLE_LOOPS, KEY_ID, KEY_QUES, KEY_ANSWER, KEY_OPTA, KEY_OPTB, KEY_OPTC, KEY_OPTD);
        String sqlQueryErrors = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT ,%s TEXT)", DB_TABLE_ERRORS, KEY_ID, KEY_QUES, KEY_ANSWER, KEY_OPTA, KEY_OPTB, KEY_OPTC, KEY_OPTD);
        String sqlQueryHistory = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)", DB_TABLE_HISTORY, KEY_ID, KEY_NANME, KEY_DATE, KEY_SCORE);
        db.execSQL(sqlQueryVariables);
        db.execSQL(sqlQueryLoops);
        db.execSQL(sqlQueryErrors);
        db.execSQL(sqlQueryHistory);

        addVariablesQuestions();
        addLoopsQuestions();
        addErrorsQuestions();
    }

    private void addErrorsQuestions() {

        Question q1 = new Question("If your program does not compile the best thing to do is:",
                "Correct the first error only and recompile",
                "Correct the final error only and recompile",
                "Correct all errors and recompile",
                "None of the above",
                "Correct the first error only and recompile"
        );

        this.addErrorsQuestionToDB(q1);

        Question q2 = new Question("What compilation error will the compiler find in this main method: Public static void main(String[] Args){;;;;;}",
                "Public should be public",
                "Args should be args",
                "Missing statements in main",
                "All of the above",
                "Public should be public"
        );

        this.addErrorsQuestionToDB(q2);


        Question q3 = new Question("What compilation error will the compiler find in this class: Class Xda{public static void main(String[] Args){}}",
                "Args should be args",
                "Class should be class",
                "Missing statements in the main method",
                "All of the above",
                "Class should be class"
        );

        this.addErrorsQuestionToDB(q3);

        Question q4 = new Question("Assuming that the variables have been properly declared, what error will the compiler find with the following statements: if (n<0) x=1; x=2; else{x=2; y=1;}",
                "unreachable statement",
                "int cannot be converted to boolean",
                "'else' without 'if'",
                "None of the above",
                "'else' without 'if'"
        );


        this.addErrorsQuestionToDB(q4);


        Question q5 = new Question("What compilation error will the compiler find with this method: static int add(int x, int y){int temp = x + y;}",
                "missing return statement",
                "variable temp should be declared static",
                "return type required",
                "None of the above",
                "missing return statement"
        );

        this.addErrorsQuestionToDB(q5);


        Question q6 = new Question("What compilation error will the compiler find with this method: static add(int x, int y){int temp = x + y; return temp;}",
                "missing return statement",
                "variable temp should be declared static",
                "return type required",
                "None of the above",
                "return type required"
        );

        this.addErrorsQuestionToDB(q6);

        Question q7 = new Question(context.getResources().getString(R.string.q9),

                "TRUE",
                "FALSE",
                "",
                "",
                "FALSE"
        );
        this.addErrorsQuestionToDB(q7);


        Question q8 = new Question("The following expression type checks: Integer.parseInt(10);",

                "TRUE",
                "FALSE",
                "",
                "",
                "FALSE"
        );
        this.addErrorsQuestionToDB(q8);


        Question q9 = new Question("The following expression type checks: int a = Math.abs(\"Camelot\".length());",

                "TRUE",
                "FALSE",
                "",
                "",
                "TRUE"
        );
        this.addErrorsQuestionToDB(q9);


        Question q10 = new Question("The following expression type checks: Integer.parseInt(\"350\");",

                "TRUE",
                "FALSE",
                "",
                "",
                "TRUE"
        );
        this.addErrorsQuestionToDB(q10);


        Question q11 = new Question("The following expression type checks: \"threefifty\".compareTo(\"350\");",

                "TRUE",
                "FALSE",
                "",
                "",
                "TRUE"
        );
        this.addErrorsQuestionToDB(q11);

        Question q12 = new Question("The following expression type checks: int z = \"threefifty\".compareTo(\"350\");",

                "TRUE",
                "FALSE",
                "",
                "",
                "TRUE"
        );
        this.addErrorsQuestionToDB(q12);


        Question q13 = new Question("The following expression type checks: \"boy\".replace('b',\"soup\".charAt(0));",

                "TRUE",
                "FALSE",
                "",
                "",
                "TRUE"
        );
        this.addErrorsQuestionToDB(q13);

        Question q14 = new Question("The following expression type checks: Math.max((\"hello\" + \" dog\").length(),5);",

                "TRUE",
                "FALSE",
                "",
                "",
                "TRUE"
        );
        this.addErrorsQuestionToDB(q14);

        Question q15 = new Question("The following expression type checks: (\"boy\".replace('b',\"soup\".charAt(0))).length();",

                "TRUE",
                "FALSE",
                "",
                "",
                "TRUE"
        );
        this.addErrorsQuestionToDB(q15);


    }

    private void addLoopsQuestions() {

        Question q1 = new Question("Why would you use a while loop to ask the user to enter a number between 0 and 9?", "Because the user might enter something that will make the program crash", "Because the user might enter a negative number", "Because the user might enter a number greater than 9", "All of the above", "All of the above");
        this.addLoopsQuestionToDB(q1);

        Question q2 = new Question("Why would you use a while loop to ask the user to enter a valid file name?", "Because the user might not enter a valid file name", "The user might enter something that could make the program crash", "In order to reject invalid entry and ask again for a valid file name", "All of the above", "All of the above");
        this.addLoopsQuestionToDB(q2);

        Question q3 = new Question("In this loop: for(int i=0;i < 3;i=i+1) System.out.println(\"*\"); What is the second part of the for loop, i < 3;, called? ", "The initialisation expression", "The final expression", "The guard", "None of the above", "The guard");
        this.addLoopsQuestionToDB(q3);

        Question q4 = new Question("In this loop: for(int i=0;i < 3;i=i+1) System.out.println(\"*\"); What is the first part of the for loop, int i=0;, called?", "The initialisation expression", "The final expression", "The guard", "None of the above", "The initialisation expression");
        this.addLoopsQuestionToDB(q4);

        Question q5 = new Question("Consider the following statement: for(;;) System.out.println(\"Hello world\"); If this statement was included in the main method of a valid program, what would its output be?", "No output because the statement will cause a compilation error", "Infinite loop - the standard output will fill up with “Hello world”, one to a line, and this will continue indefinitely", "Run time error", "None of the above", "Infinite loop - the standard output will fill up with “Hello world”, one to a line, and this will continue indefinitely");
        this.addLoopsQuestionToDB(q5);

        Question q6 = new Question("Consider the following statement: for(;;) x==1; If this statement was included in the main method of a valid program, and assuming that variable x has been declared, what would its output be?", "No output because the statement will cause a compilation error", "Infinite loop - the program will hang with no output”,Run time error", "Run time error", "None of the above", "No output because the statement will cause a compilation error");
        this.addLoopsQuestionToDB(q6);

        Question q7 = new Question("What is the output of this loop: for(int i=0;i<9;i++) System.out.print(\"*\");", "It will print 8 asterisks ('*')", "It will print 9 asterisks ('*')", "Infinite loop", "No output", "It will print 9 asterisks ('*')");
        this.addLoopsQuestionToDB(q7);


        Question q8 = new Question(context.getResources().getString(R.string.q1),
                "It will print 6 asterisks ('*')",
                "It will print 7 asterisks ('*')",
                "Infinite loop",
                "No output",
                "It will print 6 asterisks ('*')");

        this.addLoopsQuestionToDB(q8);

        Question q9 = new Question(context.getResources().getString(R.string.q2),
                "It will print 5 asterisks ('*')",
                "It will print 6 asterisks ('*')",
                "Infinite loop",
                "No output",
                "It will print 6 asterisks ('*')");
        this.addLoopsQuestionToDB(q9);

        Question q10 = new Question(context.getResources().getString(R.string.q3),
                "It will print 3 asterisks ('*')",
                "It will print 6 asterisks ('*')",
                "Infinite loop",
                "No output",
                "No output");

        this.addLoopsQuestionToDB(q10);

        Question q11 = new Question(context.getResources().getString(R.string.q4),
                "It will print 1 asterisk ('*')",
                "It will print 10 asterisks ('*')",
                "Infinite loop",
                "No output",
                "Infinite loop");
        this.addLoopsQuestionToDB(q11);


        Question q12 = new Question(context.getResources().getString(R.string.q5),
                "It will print 5 asterisk ('*')",
                "It will print 6 asterisks ('*')",
                "Infinite loop",
                "No output",
                "It will print 5 asterisk ('*')"
        );

        this.addLoopsQuestionToDB(q12);

        Question q13 = new Question(context.getResources().getString(R.string.q6),
                "It will print 4 asterisk ('*')",
                "It will print 5 asterisks ('*')",
                "Infinite loop",
                "No output",
                "It will print 5 asterisks ('*')");
        this.addLoopsQuestionToDB(q13);

        Question q14 = new Question(context.getResources().getString(R.string.q7),
                "It will print 4 asterisk ('*')",
                "It will print 5 asterisks ('*')",
                "Infinite loop",
                "No output",
                "Infinite loop"
        );

        this.addLoopsQuestionToDB(q14);


        Question q15 = new Question(context.getResources().getString(R.string.q8),
                "No output - compilation error",
                "No output - run time error",
                "No output but no errors",
                "Infinite loop",
                "Infinite loop");

        this.addLoopsQuestionToDB(q15);


    }


    private void addVariablesQuestions() {
        Question q1 = new Question("Which of the following are primitive variables:Which of the following are primitive variables?", "float", "char", "int", "All of the above", "All of the above");
        this.addVariablesQuestionToDB(q1);
        Question q2 = new Question("Which of the following are primitive variables?", "Boolean", "String", "Integer", "None of the above", "None of the above");
        this.addVariablesQuestionToDB(q2);
        Question q3 = new Question("When you make an assignment to a reference variable, the variable does not hold the value assigned, but instead points to it in memory", "TRUE", "FALSE", "", "", "TRUE");
        this.addVariablesQuestionToDB(q3);
        Question q4 = new Question("Java allows one byte (=8 bits) to store characters. This means that in Java we can represent 28 different characters.", "TRUE", "FALSE", "", "", "FALSE");
        this.addVariablesQuestionToDB(q4);
        Question q5 = new Question("Java allows two bytes (=16 bits) to store characters. This means that in Java we can represent 216 different characters?", "Because a char is both an int and a character", "Because casting has changed 97 from an int to a char", "Because the ASCII value of 'a' is 97", "All of the above", "All of the above");
        this.addVariablesQuestionToDB(q5);
        Question q6 = new Question("Why is the output of the following statements 'You typed in 107': int c=System.in.read(); System.out.println(\"You typed in \" + c)?", "Because the user entered 107", "Because 107 is the ASCII value of the first char the user entered", "Because 107 is the ASCII value of the last char the user entered", "None of the above", "Because 107 is the ASCII value of the first char the user entered");
        this.addVariablesQuestionToDB(q6);
        Question q7 = new Question("Which one of the following is a possible output of the following statements: int c=System.in.read(); System.out.println(\"You typed in \" + c)?", "If the user enters 'k' the output will be 'You typed in 107'", "If the user enters '107' the output will be 'You typed in k", "If the user enters 'k' the output will be 'You typed in k'", "If the user enters '107' the output will be 'You typed in 107'", "If the user enters 'k' the output will be 'You typed in 107'");
        this.addVariablesQuestionToDB(q7);
        Question q8 = new Question("Which one of the following is a possible output of the following statement: System.out.println('8'+'A')?", "8A", "121", "No output - compilation error", "No output - runtime error", "121");
        this.addVariablesQuestionToDB(q8);
        Question q9 = new Question("Why is the output of the following statement 'a': System.out.println((char)97)?", "Because a char is both an int and a character", "Because casting has changed 97 from an int to a char", "Because the ASCII value of 'a' is 97", "All of the above", "All of the above");
        this.addVariablesQuestionToDB(q9);


    }

    public void addVariablesQuestionToDB(Question q) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, q.getQuestion());
        values.put(KEY_ANSWER, q.getAnswer());
        values.put(KEY_OPTA, q.getOptA());
        values.put(KEY_OPTB, q.getOptB());
        values.put(KEY_OPTC, q.getOptC());
        values.put(KEY_OPTD, q.getOptD());
        dbase.insert(DB_TABLE_VARIABLES, null, values);
    }

    public void addLoopsQuestionToDB(Question q) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, q.getQuestion());
        values.put(KEY_ANSWER, q.getAnswer());
        values.put(KEY_OPTA, q.getOptA());
        values.put(KEY_OPTB, q.getOptB());
        values.put(KEY_OPTC, q.getOptC());
        values.put(KEY_OPTD, q.getOptD());
        dbase.insert(DB_TABLE_LOOPS, null, values);
    }

    public void addErrorsQuestionToDB(Question q) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, q.getQuestion());
        values.put(KEY_ANSWER, q.getAnswer());
        values.put(KEY_OPTA, q.getOptA());
        values.put(KEY_OPTB, q.getOptB());
        values.put(KEY_OPTC, q.getOptC());
        values.put(KEY_OPTD, q.getOptD());
        dbase.insert(DB_TABLE_ERRORS, null, values);
    }


    public void saveToDB(History history) {
        ContentValues values = new ContentValues();
        values.put(KEY_NANME, history.name);
        values.put(KEY_DATE, history.date);
        values.put(KEY_SCORE, history.score);

        dbase.insert(DB_TABLE_HISTORY, null, values);
    }

    public List<Question> getAllVariablesQuestions() {
        List<Question> questionList = new ArrayList<Question>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DB_TABLE_VARIABLES;
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        rowCountVariables = cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setAnswer(cursor.getString(2));
                q.setOptA(cursor.getString(3));
                q.setOptB(cursor.getString(4));
                q.setOptC(cursor.getString(5));
                q.setOptD(cursor.getString(6));

                questionList.add(q);

            } while (cursor.moveToNext());
        }
        return questionList;
    }

    public List<History> getHistory() {
        List<History> historyList = new ArrayList<History>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DB_TABLE_HISTORY;
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        rowCountVariables = cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                History history = new History();
                history.id = cursor.getInt(0);
                history.name = cursor.getString(1);
                history.date = cursor.getString(2);
                history.score = cursor.getString(3);


                historyList.add(history);

            } while (cursor.moveToNext());
        }
        return historyList;
    }


    public List<Question> getAllLoopsQuestions() {
        List<Question> questionList = new ArrayList<Question>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DB_TABLE_LOOPS;
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        rowCountLoops = cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setAnswer(cursor.getString(2));
                q.setOptA(cursor.getString(3));
                q.setOptB(cursor.getString(4));
                q.setOptC(cursor.getString(5));
                q.setOptD(cursor.getString(6));

                questionList.add(q);

            } while (cursor.moveToNext());
        }
        return questionList;
    }


    public History getLastScore() {
        dbase = this.getReadableDatabase();
        String query = String.format("SELECT * FROM " + DB_TABLE_HISTORY + " WHERE " + KEY_ID + " = (SELECT MAX(" + KEY_ID + ")  FROM " + DB_TABLE_HISTORY + " )");
        Cursor cursor = dbase.rawQuery(query, null);

        History history = null;
        if (cursor.moveToFirst()) {
            do {
                history = new History();
                history.id = cursor.getInt(0);
                history.name = cursor.getString(1);
                history.date = cursor.getString(2);
                history.score = cursor.getString(3);


            } while (cursor.moveToNext());
        }

        return history;
    }

    public List<Question> getAllErrorsQuestions() {
        List<Question> questionList = new ArrayList<Question>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DB_TABLE_ERRORS;
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        rowCountErrors = cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setAnswer(cursor.getString(2));
                q.setOptA(cursor.getString(3));
                q.setOptB(cursor.getString(4));
                q.setOptC(cursor.getString(5));
                q.setOptD(cursor.getString(6));

                questionList.add(q);

            } while (cursor.moveToNext());
        }
        return questionList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_VARIABLES);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_LOOPS);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_ERRORS);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_HISTORY);
        onCreate(db);
    }

    public int getRowCountVariables() {
        return rowCountVariables;
    }

    public int getRowCountLoops() {
        return rowCountLoops;
    }

    public int getRowCountErrors() {
        return rowCountErrors;
    }
}
