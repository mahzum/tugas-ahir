package kelompok.uts;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class kuis extends AppCompatActivity {

    public static final String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static final int PERMISSIONS_ALL = 1;
    Button answer1, answer2, answer3, answer4;
    TextView score, question, level;
    private int countGrantedPermission = 0;
    private boolean Read = false;
    private Question mQuestions = new Question();
    private String mAnswer;
    //private int mQuestionsLength = mQuestions.mQuestions.length;
    private int mScore = 0;
    private int highScore;
    private int jumlahSoal = -1;
    private int lvl = 1;
    private List<Integer> soalAcak = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);
        for (int i = 0; i < 25; i++) {
            soalAcak.add(i);
            Log.d("Kuis", "Angka " + i);
        }
        Collections.shuffle(soalAcak);
        for (int i = 0; i < 25; i++) {

            Log.d("Kuis", "Angka Acak " + soalAcak.get(i));
        }
        Log.d("Kuis", "Jumlah " + soalAcak.size());
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        level = (TextView) findViewById(R.id.level);
        score = (TextView) findViewById(R.id.score);
        question = (TextView) findViewById(R.id.question);

        score.setText("Score: " + mScore);

        updateQuestion();
        level.setText("Level " + lvl);
        Log.d("Kuis", "SOAL " + soalAcak.get(jumlahSoal));
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer1.getText() == mAnswer) {
                    mScore++;
                    score.setText("Score: " + mScore);
                }
                updateQuestion();
                /*} else {
                    gameOver();
                }*/
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer2.getText() == mAnswer) {
                    mScore++;
                    score.setText("Score: " + mScore);
                }
                updateQuestion();
                /*} else {
                    gameOver();
                }*/
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer3.getText() == mAnswer) {
                    mScore++;
                    score.setText("Score: " + mScore);
                }
                updateQuestion();
                /*} else {
                    gameOver();
                }*/
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer4.getText() == mAnswer) {
                    mScore++;
                    score.setText("Score: " + mScore);
                }

                updateQuestion();
                /*} else {
                    gameOver();
                }*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.exit) {
            exitGame();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateQuestion() {
        jumlahSoal++;
        if (jumlahSoal >= 25) {
            finishGame();
        } else {
            if ((jumlahSoal + 1) % 6 == 0) {
                lvl++;
                level.setText("Level " + lvl);
            }
            int num = soalAcak.get(jumlahSoal);
            question.setText(mQuestions.getQuestion(num));
            answer1.setText(mQuestions.getChoice1(num));
            answer2.setText(mQuestions.getChoice2(num));
            answer3.setText(mQuestions.getChoice3(num));
            answer4.setText(mQuestions.getChoice4(num));
            mAnswer = mQuestions.getCorrectAnswer(num);

        }

    }



    private void exitGame() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(kuis.this);
        alertDialogBuilder
                .setTitle("Exit Game")
                .setMessage("Are you sure want to exit game?")
                .setCancelable(false)
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
                            }
                        })
                .setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void finishGame() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Read = true;
        ChekPremission();
        if (highScore < mScore) {
            Read = false;
            ChekPremission();
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(kuis.this);
        alertDialogBuilder
                .setMessage("Success! Your score is " + mScore + " points.")
                .setCancelable(false)
                .setPositiveButton("NEW GAME",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), kuis.class));
                                finish();
                            }
                        })
                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(new Intent(getApplicationContext(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void ChekPremission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            countGrantedPermission = 0;
            for (String permission : PERMISSIONS) {
                if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                    countGrantedPermission++;
                }
            }
        } else {
            countGrantedPermission = 2;
        }

        if (countGrantedPermission != 2) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSIONS_ALL);
        } else {
            CheckResultPermissions();
        }
    }

    private void ChekHigscore() {

        File fileDir = new File(Environment.getExternalStorageDirectory(),"UTS" );
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            File file = new File(fileDir, "HighScore.txt");
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            fis.close();
            highScore = Integer.parseInt(line);

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            Toast.makeText(this, "can't Check High Score", Toast.LENGTH_SHORT).show();
        } catch (IOException e1) {
            e1.printStackTrace();
            Toast.makeText(this, "can't Check High Score", Toast.LENGTH_SHORT).show();
        }
        catch (NumberFormatException e){
            Toast.makeText(this, "can't Check High Score", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_ALL) {
            countGrantedPermission = 0;

            if (grantResults.length > 0) {

                for (int i = 0; i < grantResults.length; i++) {
                    if (permissions[i].equals(android.Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        countGrantedPermission++;
                    } else if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        countGrantedPermission++;
                    }
                }
                CheckResultPermissions();
            }
        } else {
            CheckResultPermissions();
        }
    }

    private void CheckResultPermissions() {
        if (countGrantedPermission == 2) {
            if (Read) {
                ChekHigscore();
            } else {
                WriteHighScore();
            }
        } else {
            if (Read) {
                Toast.makeText(this, "can't Check High Score", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "can't Write High Score", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void WriteHighScore() {
        File fileDir = new File(Environment.getExternalStorageDirectory(),"UTS" );
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            File file = new File(fileDir, "HighScore.txt");
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(String.valueOf(mScore));
            osw.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "can't Write High Score", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeTe;xt(this, "can't Write High Score", Toast.LENGTH_SHORT).show();
        }
    }
}
