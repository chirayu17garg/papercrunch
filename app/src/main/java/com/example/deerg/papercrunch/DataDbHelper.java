package com.example.deerg.papercrunch;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataDbHelper extends SQLiteOpenHelper {



    public static final String DbName="LEVEL";
    public static final int DbVersion=1;
    public static final String CREATE_TABLE="create table quiz" +
            " (id int primary key,question text ,option1 text,option2 text,option3 text,answer text,hint text,stars int,subLevel_id int);";

    public DataDbHelper(Context context)
    {
        super(context,DbName,null,DbVersion);
        Log.d("db operations","Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        insertInValues(db);
        Log.d("db operations","Table created");
    }

    public void checktable(SQLiteDatabase db){

        db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from sqlite_master", null);
        if(cursor.getCount()==0) {
            onCreate(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addData(int id,String question,String option1,String option2,String option3,String answer,String hint, int stars,int subLevel_id,SQLiteDatabase db)
    {
        ContentValues cv=new ContentValues();
        cv.put("id",id);
        cv.put("question",question);
        cv.put("option1",option1);
        cv.put("option2",option2);
        cv.put("option3",option3);
        cv.put("answer",answer);
        cv.put("hint",hint);
        cv.put("stars",stars);
        cv.put("subLevel_id",subLevel_id);

        db.insert("quiz",null,cv);

        Log.d("db operations","one row inserted");
    }

    public void insertInValues(SQLiteDatabase db)
    {
        addData(1,"What will the following command print?\nprintf(\"This is a line\");\nprintf(\"This is also a line\");",
                "This is a line\nThis is also a line","This is a lineThis is also a line","This is   a line. This is also a line",
                "This is a lineThis is also a line","Since there is no '\\n', the cursor would never go to the next line and would continue printing in the same line",0,4,db);

        addData(2,"What will the following command print?\nprintf(\"Hi\\n, there\");",
                "Hi, there","Hi,\nthere","Hi,    \nthere",
                "Hi,    \nthere","Whenever the program finds a '\\n' in output, the cursor shifts to the next line, printing everything after it in a new line",0,4,db);

        addData(3,"Which of the following is not a valid variable name in C?",
                "Django","1Python","_Jojah",
                "1Python","Variable names cannot start with numbers or special characters.",0,6,db);

        addData(4,"Which is a valid variable name?",
                "uufhe,de","3kodikom__23","forensic",
                "forensic","Variable names cannot have any special characters other than underscore and cannot start with a number.",0,6,db);

        addData(5,"Which of the following will store the value 10 in the variable hello ?\ni) int hello = 10;  ii) int hello = 10.34;  iii) int hello = 10.0;",
                "Options i and ii","Options i and iii","All of the above",
                "All of the above","As soon as we assign the data type of any variable 'int', it retains only the integer part of the value it is assigned",0,7,db);

        addData(6,"What should be stored in the variable q in this?\ndouble q = 10 divided by 5",
                "2","2.0","Nothing",
                "2.0","Variable with data type double will store floating point numbers only, not integers",0,7,db);

        addData(7,"What is the final value stored in a in the following code given?\nint a = -10;\na = a + 10;",
                "0","20","10",
                "0","We are adding 10 to -10, thus resulting in 0",0,9,db);

        addData(8,"What is the value of x in this C code?\nint x = 4 *5 / 2 + 9;",
                "6.75","1.85","19",
                "19","Multiplication and division are performed first and then addition and subtraction.",0,9,db);

        addData(9,"What is the output of the following?\nint i = 3;\ni--;\ni++;",
                "3","4","2",
                "3","We are first decreasing the value of i by 1 and then increasing it by 1, resulting in the initial value, 3.",0,9,db);

        addData(10,"What is the output of the following?\nint testInteger;\nscanf(\"%d\",&testInteger);\nprintf(\"Number = %d\",testInteger);\nI've input 4",
                "4","Number = 4","Error",
                "Number = 4","The 4 inputted using scanf has been printed using printf. No error.",0,11,db);

        addData(11,"What is the output of the following?\n int integer = 9876;\nfloat decimal = 987.6543;\nint a = integer + decimal;\nprintf(%d\\n\", a);",
                "10863","10863.6543","10864",
                "10863","Integer variables only store the integer parts of the values assigned to them. So, a will store only the integer part of 10863.6543.",0,11,db);

        addData(12,"What is the output of the following?\nint a = 10;\nint b = 11;\nprintf(a != b);",
                "TRUE","FALSE","Error",
                "TRUE","Since 10 is not equal to 11, the code would print True.",0,12,db);

        addData(13,"What is the output of the following?\nchar a ='s';\nchar b = 'r';\nprintf(a<b);",
                "TRUE","FALSE","Error",
                "FALSE","Since 's' comes after 'r' in alphabetical order, the statement 'a<b' becomes false.",0,12,db);

        addData(14,"What is the output of the following?\nint a = 1;\nint b = 1;\nint c = a || --b;\nint d = a-- && --b;\nprintf(\"a = %d, b = %d, c = %d, d = %d\", a, b, c, d); ",
                "a = 0, b = 1, c = 1, d = 0","a = 0, b = 0, c = 1, d = 0","a = 1, b = 1, c = 1, d = 1",
                "a = 0, b = 0, c = 1, d = 0","c is assigned 1 since a is 1 and 'a || --b' becomes true. d is assigned 0 because 'a-- && --b' is 0 because of 0 value of '--b'. The value of a also reduces to 0 after the execution of this statement.",0,13,db);

        addData(15,"What is the output of the following?\nint a =5;\nint b = 10;\nif (b/a == 2)\n{\nprintf(\"hi \");\n}\nprintf(\"there\");\n",
                "hi","there","hi there",
                "hi there","Since 10/5 is equal to 2, the control will move inside the block , thus printing 'hi ' before printing 'there'",0,15,db);

        addData(16,"Will the control enter the block?\nint a = 19;\nint b = 8;\nint c = 10\nif ((a > c) && (b > c))\n{\n}",
                "Yes","No","Can't say",
                "No","The second condition, 'b > c' is false, so the entire expression would become false.",0,15,db);

        addData(17,"What's the output?\nint x = 5;\nif (x == 5)\nprintf(\"Equals 5\");\nelse\nprintf(\"I don't know\");",
                "Equals 5","Less than 1","I don't know",
                "Equals 5","Since x is equal to 5, the control would enter the first conditional statement and print 'Equals 5'",0,16,db);

        addData(18,"What is the output?\nint x = 10;\nint y = 11;\nif(x>y)\nprintf(\"x is greater than y\");\nelse\nprintf(\"y is greater than x\");",
                "x is greater than y","y is greater than x","Error",
                "y is greater than x","Since 10 is less than 11, the else clause would be operated and the code will print 'y is greater than x'",0,16,db);

        addData(19,"What is the output?\nint perc = 84\nif(perc>=90)\nprintf(\"Grade A\");\nelse if(perc>=80 && perc <90)\nprintf(\"Grade B\");\nelse if(perc>=70 && perc <80)\nprintf(\"Grade C\");\nelse printf(\"Fail\");",
                "Grade B","Grade C","Fail",
                "Grade B","Since perc lies in the range 'perc>=80 && perc <90', the code will print 'Grade B'",0,17,db);

        addData(20,"What is the output?\nint num=2;\nswitch(num+2)\n{\ncase 1:\nprintf(\"Case1: Value is: %d\", num); break;\ncase 2:\nprintf(\"Case2: Value is: %d\", num); break;\ncase 3:\nprintf(\"Case3: Value is: %d\", num); break;\ndefault:\nprintf(\"Default: Value is: %d\", num);\n}",
                "Case2: Value is: 2","Default: value is: 2","Error",
                "Default: value is: 2","Since there is no matching case for the value 4, the default case will execute.",0,18,db);

        addData(21,"What is the output?\nint i=2;\nswitch (i)\n{\ncase 1:\nprintf(\"Case1\");\ncase 2:\nprintf(\"Case2\");\ncase 3:\nprintf(\"Case3\");\ncase 4:\nprintf(\"Case4\");\ndefault:\nprintf(\"Default\");\n}",
                "Case2 Case3 Case4 Default","Case 2","Default",
                "Case2 Case3 Case4 Default","The matching case is 2 so the code will start printing from that case. Due to the absence of 'break' statement, the code will keep printing till it encounters break statement or reaches the end of switch case.",0,18,db);

        addData(22,"What's the output?\n#include <stdio.h>\nvoid main()\n{\nint tally;\nfor(tally=0;tally<10;++tally)\n{\nprintf(\"#\");\nif(tally>6)\ncontinue;\nprintf(\"%d\",tally);\n}\n}",
                "#0#1#2#3#4#5#6###","#0#1#2#3#4#5#6#7#8#9#10","#0#1#2#3#4#5##7#8#9#10",
                "#0#1#2#3#4#5#6###","The code will print numbers till they become equal to 6. After this, the continue statement will prevent the printing of the numbers and only the '#' will be printed.",0,20,db);

        addData(23,"What is the output?\n#include <stdio.h>\nvoid main()\n{\nint tally;\nfor(tally=100;tally != 98 ;--tally)\n{\nprintf(tally);\n}\n}",
                "100101012103104...","1009998","10099",
                "10099","The code will print only till tally is greater than 98. So, only two iterations.",0,20,db);

        addData(24,"'Short' is a data type which stores integers and its range is -32,768 to 32,767. Due to such small range, the data type is called 'short'. Now what will be the output?\nshort i;\nfor (i = 1; i> 0; i++)\n{\nprintf(\"%d\\n\", i);\n}",
                "The control won’t fall into the for loop","Numbers will be displayed until the signed limit\n of short and throw a run time error","Numbers will be displayed until the signed limit\n of short and program will successfully terminate",
                "Numbers will be displayed until the signed limit\n of short and program will successfully terminate","The loop will run indefinitely but short has limited range and will force the loop to be terminated as soon as its limit is reached.",0,20,db);

        addData(25,"What is the output?\nint i = 0, j = 0;\nwhile (i<5 || j<10) \n{\ni++;\nj++;\n}\nprintf(\"%d %d\", i, j);",
                "5 5","5 10","10 10",
                "10 10","The loop will run till either i is less than 5 or j is less than 10. In simpler terms, it will continue till j is less than 10. So the solution is '10 10'",0,21,db);

        addData(26,"What is the output?\nint i = 0, j = 0;\nwhile (i<5 && j<10) \n{\ni++;\nj++;\n}\nprintf(\"%d %d\", i, j);",
                "5 5","10 10","0 0",
                "5 5","The loop will continue till i is less than 5 and j is less than 10. In simpler terms, it will continue till i is less than 5. So the soln. is '5 5'",0,21,db);

        addData(27,"What is the output?\n#include <stdio.h>\nint main()\n{\nint loop=10;\nwhile(printf(\"Hello \") && loop--);\n}\nint tally;\nfor(tally=100;tally != 98 ;--tally)\n{\nprintf(tally);\n}\n}",
                "Hello Hello Hello Hello ... (Infinite times)","Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello (10 times)","Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello (11 times)",
                "Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello (10 times)","The value returned by printf(\"Hello\") is always true. So the loop will be executed will the second argument becomes 0('false'). Remember that '--' is written after the operand, implying that the value stored in the variable would be used first and then updated.",0,21,db);

        addData(28,"What is the output?\n#include <stdio.h>\nvoid main()\n{\nint cnt=1;\ndo\n{\nprintf(\"%d,\",cnt);\ncnt+=1;\n}\nwhile(cnt>=10);\nprintf(\"\\nAfter loop cnt=%d\",cnt);\n}",
                "After loop cnt=1","1\nAfter loop cnt=2","After loop cnt=2",
                "1\nAfter loop cnt=2","do-while loops execute atleast once. After the first execution the condition comes out to be false and the loop breaks.",0,22,db);

        addData(29,"What is the output?\n#include <stdio.h>\nint fun(int n)\n{\nfor(;n > 0; n--)\nprintf(\"GeeksQuiz \");\nreturn 0;\n}\nvoid main()\n{\nfun(3);\n}",
                "Error","GeeksQuiz GeeksQuiz","GeeksQuiz GeeksQuiz GeeksQuiz",
                "GeeksQuiz GeeksQuiz GeeksQuiz","The main function calls the fun function passing 3 as a parameter. The for loop in fun will print till n is positive, i.e. 3 times.\n",0,24,db);

        addData(30,"Functions can return more than one value",
                "True","False","Can't say",
                "False","Functions cannot return multiple values.",0,24,db);


    }


    public int readCount(int subid,SQLiteDatabase db)
    {
        db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT COUNT(id) AS count FROM Quiz GROUP BY subLevel_id having subLevel_id="+subid,null);
        cursor.moveToFirst();
        int count=0;
        if(cursor.moveToFirst())
        count=cursor.getInt(cursor.getColumnIndex("count"));
        return count;
    }
    public String[] readQuestion(int subid,SQLiteDatabase db)
    {
        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT question FROM Quiz WHERE subLevel_id="+subid,null);

        int a=readCount(subid,db);
        String arr[]=new String[a];

        int i=0;
        while(cursor.moveToNext())
        {
            arr[i]=cursor.getString(cursor.getColumnIndex("question"));
            i++;
        }
        return arr;
    }

    public String[] readOption1(int subid,SQLiteDatabase db)
    {
        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT option1 FROM Quiz WHERE subLevel_id="+subid,null);

        int a=readCount(subid,db);
        String arr[]=new String[a];

        int i=0;
        while(cursor.moveToNext())
        {
            arr[i]=cursor.getString(cursor.getColumnIndex("option1"));
            i++;
        }
        return arr;
    }

    public String[] readOption2(int subid,SQLiteDatabase db)
    {
        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT option2 FROM Quiz WHERE subLevel_id="+subid,null);

        int a=readCount(subid,db);
        String arr[]=new String[a];

        int i=0;
        while(cursor.moveToNext())
        {
            arr[i]=cursor.getString(cursor.getColumnIndex("option2"));
            i++;
        }
        return arr;
    }

    public String[] readOption3(int subid,SQLiteDatabase db)
    {
        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT option3 FROM Quiz WHERE subLevel_id="+subid,null);

        int a=readCount(subid,db);
        String arr[]=new String[a];

        int i=0;
        while(cursor.moveToNext())
        {
            arr[i]=cursor.getString(cursor.getColumnIndex("option3"));
            i++;
        }
        return arr;
    }
    public String[] readAnswer(int subid,SQLiteDatabase db)
    {
        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT answer FROM Quiz WHERE subLevel_id="+subid,null);

        int a=readCount(subid,db);
        String arr[]=new String[a];

        int i=0;
        while(cursor.moveToNext())
        {
            arr[i]=cursor.getString(cursor.getColumnIndex("answer"));
            i++;
        }
        return arr;
    }
    public String[] readHint(int subid,SQLiteDatabase db)
    {
        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT hint FROM Quiz WHERE subLevel_id="+subid,null);

        int a=readCount(subid,db);
        String arr[]=new String[a];

        int i=0;
        while(cursor.moveToNext())
        {
            arr[i]=cursor.getString(cursor.getColumnIndex("hint"));
            i++;
        }
        return arr;
    }

    public int getQid(int subid, int index, SQLiteDatabase db)
    {
        int sum =0;
        for(int i=1;i<subid;i++)
        {
            sum=sum+readCount(i,db);
        }
        sum=sum+index+1;
        return sum;
    }
    public void updateStars(int qid,int tries,SQLiteDatabase db)
    {
        db=this.getWritableDatabase();
        int stars=3-tries;
        if(stars<1)
            stars=1;

        db.rawQuery("UPDATE Quiz SET stars="+stars+" WHERE id="+qid,null);
    }
    public int getStars(SQLiteDatabase db)
    {
        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(stars) AS sum from Quiz",null);
        int stars=0;
        if(cursor.moveToFirst())
            stars=cursor.getInt(cursor.getColumnIndex("sum"));
        return stars;
    }



}
