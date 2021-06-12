package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText inputGO;
    TextView what_you_do;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputGO = findViewById(R.id.inputGO);
        what_you_do = findViewById(R.id.what_you_do);
        out();
    }
    public static int n=3;// размер поля
    public static int[][] game_desk = new int[n][n];

    public void out() {
        String str = "";
        TextView all = findViewById(R.id.all);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                str += game_desk[j][i] + "     ";
            }
            str += "\n";
        }
        all.setText(str);
        inputGO.setText("");
    }

    public void go(View view){
        String s= inputGO.getText().toString();
        int x=Integer.parseInt(String.valueOf(s.charAt(0)));
        int y=Integer.parseInt(String.valueOf(s.charAt(2)));
        if (game_desk[x][y]==0) game_desk[x][y]=1;
        out();
        goAI();
        out();
        String whoISwinner=checkWIN();
        if(whoISwinner!="") what_you_do.setText(whoISwinner);

    }

    void goAI(){// пока ИИ выбирает ход случайно
        //try{TimeUnit.SECONDS.sleep(3);}catch (Exception e){}// искуственный интеллект думает
        int x,y;
        do {
            x = (int) (Math.random() * n);
            y = (int) (Math.random() * n);
        }while (game_desk[x][y]!=0);
        game_desk[x][y]=5;
    }

    String checkWIN(){
        int sum=0;
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) sum += game_desk[i][j];
            if (sum == 3) return "You are win";
            else if(sum==15)  return "AI is win";
        }
        sum=0;
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) sum += game_desk[j][i];
            if (sum == 3) return "You are win";
            else if(sum==15) return "AI is win";
        }
        sum=0;
        for(int i=0;i<n;i++) {
            sum += game_desk[i][i];
            if (sum == 3) return "You are win";
            else if(sum==15) return "AI is win";
        }
        sum=0;
        for(int i=0;i<n;i++) {
            sum += game_desk[i][n-1-i];
            if (sum == 3) return "You are win";
            else if(sum==15) return "AI is win";
        }
        return "";
    }
}