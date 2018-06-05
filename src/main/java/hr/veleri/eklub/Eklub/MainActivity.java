package hr.veleri.eklub.Eklub;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showError(String text, Context c){
        Toast errorToast = Toast.makeText(c, text, Toast.LENGTH_LONG);
        errorToast.show();
    }
}
