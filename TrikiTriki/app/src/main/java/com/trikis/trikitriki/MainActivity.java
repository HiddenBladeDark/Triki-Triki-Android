package com.trikis.trikitriki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //matriz botones
    private Button[][] buttons=new Button[3][3];
    //turno player1
    private boolean player1Turn=true;
    //rondas
    private int roundCounts=0;
    //puntos del player1
    private int player1points=0;
    //puntos del player2
    private int player2points=0;
    //actualizar texto
    private TextView textviewplayer1;
    private TextView textviewplayer2;
    //resetear boton partida nueva
    private Button reset_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pasamos los datos xml a variables
        textviewplayer1=(TextView)findViewById(R.id.text_view_player1);
        textviewplayer2=(TextView)findViewById(R.id.text_view_player2);

        reset_button=(Button)findViewById(R.id.reset_button);

        //REINICIAR JUEGO
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resetGame();
            }
        });



        //rellenar matriz
        buttons[0][0]=(Button)findViewById(R.id.button_00);
        buttons[0][1]=(Button)findViewById(R.id.button_01);
        buttons[0][2]=(Button)findViewById(R.id.button_02);
        buttons[1][0]=(Button)findViewById(R.id.button_10);
        buttons[1][1]=(Button)findViewById(R.id.button_11);
        buttons[1][2]=(Button)findViewById(R.id.button_12);
        buttons[2][0]=(Button)findViewById(R.id.button_20);
        buttons[2][1]=(Button)findViewById(R.id.button_21);
        buttons[2][2]=(Button)findViewById(R.id.button_22);

        //recorremos matriz del boton

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setOnClickListener(this);

            }
        }
    }


    //evento onclick para cada boton x y o
    @Override
    public void onClick(View v) {

        Button b=(Button) v;

        //equals para comparar dos objetos si apuntan al mis objeto
        if(!b.getText().toString().equals(""))
        {
            return;
        }

        if(player1Turn)
        {
            b.setText("X");
        }
        else
        {
            b.setText("O");
        }
        //contamos las rondas
        roundCounts++;


        //validacion ganador
        if(checkforWin())
        {
            if(player1Turn)
            {
                player1Wins();
            }
            else
            {
                player2Wins();
            }
        }
        else
        {
            //empate
            if(roundCounts==9)
            {
                draw();
            }
            else
            {
                player1Turn=!player1Turn;
            }
        }

    }

    //metodo empate
    private void draw() {
        //toat make para que nos muestre un mensaje de alerta
        Toast.makeText(this,"EMPATE",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    //metodo jugador 2 gana
    private void player2Wins() {
        player2points++;
        //toat make para que nos muestre un mensaje de alerta
        Toast.makeText(this,"Jugador 2 GANA!!!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }

    //metodo jugador 1 gana
    private void player1Wins() {
        player1points++;
        //toat make para que nos muestre un mensaje de alerta
        Toast.makeText(this,"Jugador 1 GANA!!!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }

    //metodo actualizar points
    private void updatePointsText()
    {
        textviewplayer1.setText("JUGADOR 1 : "+player1points);
        textviewplayer2.setText("| JUGADOR 2 : "+player2points);

    }
    //metodo resetear app
    private void resetBoard()
    {
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                buttons[i][j].setText("");


         roundCounts=0;
         player1Turn=true;
    }

    private void resetGame(){
        player1points=0;
        player2points=0;
        updatePointsText();
        resetBoard();
    }





    //verificar el ganador
    private boolean checkforWin()
    {
        //creamos matriz
        String[][] field=new String[3][3];
        //recorremos la matriz para agregarla a field
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                field[i][j]=buttons[i][j].getText().toString();

        //equals para comparar dos objetos si apuntan al mis objeto
        //recorremos la matriz field comparando cada objeto que contenga
        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
            {
                return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
            {
                return true;
            }
        }
        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
        {
            return true;
        }
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
        {
            return true;
        }
        return false;

    }
}
