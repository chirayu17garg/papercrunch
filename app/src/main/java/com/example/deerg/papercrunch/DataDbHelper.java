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
        Log.d("db operations","Table created");
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
        db= this.getWritableDatabase();
        addData(1,"What will the following command print?\nprintf(\"This is a line\");\nprintf(\"This is also a line\");",
                "This is a line\nThis is also a line","This is a lineThis is also a line","This is   a line. This is also a line",
                "This is a lineThis is also a line","",0,4,db);

        addData(2,"What will the following command print?\nprintf(\"Hi\\n, there\");",
                "Hi, there","Hi,\nthere","Hi,    \nthere",
                "Hi,    \nthere","",0,4,db);

        addData(3,"Which of the following is not a valid variable name in C?",
                "Django","1Python","_Jojah",
                "1Python","",0,6,db);

        addData(4,"Which is a valid variable name?",
                "uufhe,de","3kodikom__23","forensic",
                "forensic","",0,6,db);

        addData(5,"Which of the following will store the value 10 in the variable hello ?\ni) int hello = 10;  ii) int hello = 10.34;  iii) int hello = 10.0;",
                "Options i and ii","Options i and iii","All of the above",
                "All of the above","",0,7,db);

        addData(6,"What should be stored in the variable q in this?\ndouble q = 10 divided by 5",
                "2","2.0","Nothing",
                "2.0","",0,7,db);

        addData(7,"What is the final value stored in a in the following code given?\nint a = -10;\na = a + 10;",
                "0","20","10",
                "0","",0,9,db);

        addData(8,"What is the value of x in this C code?\nint x = 4 *5 / 2 + 9;",
                "6.75","1.85","19",
                "19","",0,9,db);

        addData(9,"What is the output of the following?\nint i = 3;\ni--;\ni++;",
                "3","4","2",
                "3","",0,9,db);

        addData(10,"What is the output of the following?\nint testInteger;\nscanf(\"%d\",&testInteger);\nprintf(\"Number = %d\",testInteger);\nI've input 4",
                "4","Number = 4","Error",
                "Number = 4","",0,11,db);

        addData(11,"What is the output of the following?\n int integer = 9876;\nfloat decimal = 987.6543;\nint a = integer + decimal;\nprintf(%d\\n\", a);",
                "10863","10863.6543","10864",
                "10863","",0,11,db);

        addData(12,"What is the output of the following?\nint a = 10;\nint b = 11;\nprintf(a != b);",
                "TRUE","FALSE","Error",
                "TRUE","",0,12,db);

        addData(13,"What is the output of the following?\nchar a ='s';\nchar b = 'r';\nprintf(a<b);",
                "TRUE","FALSE","Error",
                "FALSE","",0,12,db);

        addData(14,"What is the output of the following?\nint a = 1;\nint b = 1;\nint c = a || --b;\nint d = a-- && --b;\nprintf(\"a = %d, b = %d, c = %d, d = %d\", a, b, c, d); ",
                "a = 0, b = 1, c = 1, d = 0","a = 0, b = 0, c = 1, d = 0","a = 1, b = 1, c = 1, d = 1",
                "a = 0, b = 0, c = 1, d = 0 ","",0,13,db);

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

}
