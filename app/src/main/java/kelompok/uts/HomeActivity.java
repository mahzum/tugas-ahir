package kelompok.uts;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences preferences;
    private int countGrantedPermission = 0;
    int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ChekPremission();
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText("HIGH SCORE : " + highScore);
    }

    /*button*/
    public void mulai(View view) {
        Intent intent = new Intent(HomeActivity.this, kuis.class);
        startActivity(intent);
    }

    private void ChekHigscore() {

        File fileDir = new File(Environment.getExternalStorageDirectory(), "UTS");
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
        } catch (IOException e1) {
            e1.printStackTrace();
            Toast.makeText(this, "can't Check High Score", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "can't Check High Score", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == kuis.PERMISSIONS_ALL) {
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

    private void ChekPremission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            countGrantedPermission = 0;
            for (String permission : kuis.PERMISSIONS) {
                if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                    countGrantedPermission++;
                }
            }
        } else {
            countGrantedPermission = 2;
        }

        if (countGrantedPermission != 2) {
            ActivityCompat.requestPermissions(this, kuis.PERMISSIONS, kuis.PERMISSIONS_ALL);
        } else {
            CheckResultPermissions();
        }
    }

    private void CheckResultPermissions() {
        if (countGrantedPermission == 2) {

            ChekHigscore();

        } else {
            Toast.makeText(this, "can't Check High Score", Toast.LENGTH_SHORT).show();

        }
    }
}
