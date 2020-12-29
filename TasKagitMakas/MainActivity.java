package com.example.taskgtmakasoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button t_rock,t_paper,t_scissor;//Tuşlar
    ImageView el_cpu,el_ben;//Resimler

    String ben,comp,result;//Skor tablosu bileşenleri
    Random r;//Random fonksiyonu
    TextView skor_tablosu;//Skor tablosu
    int Ben,Cpu= 0;//Skorun tutulması için sayaç kısmı


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Resimlerin Çağırılması
        el_cpu= findViewById(R.id.el_cpu);
        el_ben= findViewById(R.id.el_ben);


        //Tuşların atanması
        t_rock= findViewById(R.id.t_rock);
        t_paper= findViewById(R.id.t_paper);
        t_scissor= findViewById(R.id.t_scissor);

        //Skortablosu
        skor_tablosu= findViewById(R.id.skor_tablosu);

        //Cpu için random fonksiyonu
        r=new Random();
        Button t_reset=findViewById(R.id.t_reset);
          t_reset.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                       // AlertDialog Yapımı
                       AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        //Alert dialogun title'i
                        builder.setTitle("Sıfırlama işlemi.");

                       // Son mesaj
                        builder.setMessage("Eminmisiniz?");

                        // Evete basılınca yapılıcak işlem
                        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                           public void onClick(DialogInterface dialog, int which) {
                                // Evet işlemi

                                resetGame();
                            }
                       });

                        // Hayıra basılırsa yapılıcak işlem
                        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                           public void onClick(DialogInterface dialog, int which) {
                                //Hayır işlemi
                                Toast.makeText(getApplicationContext(),
                                        "Hayıra bastınız deavam ediyoruz",Toast.LENGTH_SHORT).show();
                            }
                        });

                       AlertDialog dialog = builder.create();

                        dialog.show();
                    }
                });



        //Tuşların Kullanılması ve skor tablosunun yenilenmesi
        t_rock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ben="Taş";
                el_ben.setImageResource(R.drawable.rock);
                calculate();
                skor_tablosu.setText(("Ben:")+Ben +"\nCpu:"+ Cpu);
            }
        });
        t_paper.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ben="Kagıt";
                el_ben.setImageResource(R.drawable.paper);
                calculate();
                skor_tablosu.setText(("Ben:")+ Ben +"\nCpu:"+ Cpu);
            }
        });t_scissor.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ben="Makas";
                el_ben.setImageResource(R.drawable.scissor);
                calculate();
                skor_tablosu.setText(("Ben:")+ Ben +"\nCpu:"+ Cpu);
            }

        });
    }


    //Cpu nun Seçim Yapma Kısmı
    private void calculate() {
        int cpu=r.nextInt(3);
        if(cpu==0){
            comp="Taş";
            el_cpu.setImageResource(R.drawable.rock);
            }else if (cpu==1){
            comp="Kagıt";
            el_cpu.setImageResource(R.drawable.paper);
            }else if (cpu==2){
            comp="Makas";
            el_cpu.setImageResource(R.drawable.scissor);
        }


        //Seçimlere göre kazanan ve kaybedenin belirlendiği kısım
        {
            if (ben.equals("Taş") && comp.equals("Kagıt"))
            {   Cpu++;
                result= "Kagıt Taşı Sarar,Kaybettin";
            }else if (ben.equals("Taş") && comp.equals("Makas"))
            {   Ben++;
                result= "Taş Makası Kırar,Kazandın";
            }else if (ben.equals("Kagıt") && comp.equals("Taş"))
            {   Ben++;
                result= "Kağıt Taşı Sarar,Kazandın";
            }else if (ben.equals("Kagıt") && comp.equals("Makas"))
            {   Cpu++;
                result= "Makas Kağıtı Keser,Kaybettin";
            }else if (ben.equals("Makas") && comp.equals("Taş"))
            {   Cpu++;
                result= "Taş Makası Kırar,Kaybettin";
            }else if (ben.equals("Makas") && comp.equals("Kagıt"))
            {   Ben++;
                result= "Makas Kagıtı Keser,Kazandın";
            }else if (ben.equals("Makas") && comp.equals("Makas"))
            {result= "Berabere";
            }else if (ben.equals("Taş") && comp.equals("Taş"))
            {result= "Berabere";
            }else if (ben.equals("Kagıt") && comp.equals("Kagıt"))
            {result= "Berabere";
            }

            //Mesajın Ekrana Gösterilmesi
           // Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(25);
            toast.show();
        }
}

    //Oyunu Sıfırlama Bölümü
    private void resetGame(){
            Ben= 0;
            Cpu= 0;
            skor_tablosu.setText(("Ben:0") +"\nCpu:0");

    }
}
