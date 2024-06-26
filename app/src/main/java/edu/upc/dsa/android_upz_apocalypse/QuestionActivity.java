package edu.upc.dsa.android_upz_apocalypse;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class QuestionActivity extends AppCompatActivity{
    // From activity
    TextView dateVal;
    TextView messageVal;
    TextView titleVal;
    TextView senderVal;

    Button entregaButton;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        dateVal = findViewById(R.id.dateVal);
        messageVal = findViewById(R.id.messageVal);
        titleVal = findViewById(R.id.titleVal);
        senderVal = findViewById(R.id.senderVal);
        entregaButton = findViewById(R.id.entregaButton);

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        entregaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(dateVal.getText().toString()) || TextUtils.isEmpty(messageVal.getText().toString()) || TextUtils.isEmpty(titleVal.getText().toString())|| TextUtils.isEmpty(senderVal.getText().toString())) {
                    String message = "All inputs are needed";
                    Toast.makeText(QuestionActivity.this, message, Toast.LENGTH_LONG).show();
                } else {
                    Question consulta = new Question();
                    consulta.setDate(dateVal.getText().toString());
                    consulta.setMessage(messageVal.getText().toString());
                    consulta.setTitle(titleVal.getText().toString());
                    consulta.setSender(senderVal.getText().toString());
                    realizarConsulta(consulta);

                }
            }

        });
    }

    public void realizarConsulta(Question consulta) {
        Call<Void> q = ApiClient.getService().realizarConsulta(consulta);
        q.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    String message = "Successful";
                    Toast.makeText(QuestionActivity.this, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
                    startActivity(new Intent(QuestionActivity.this, MainActivity.class));

                } else {
                    String message = "An error occurred";
                    Toast.makeText(QuestionActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("NO", "onFailure", t);
                String message = t.getLocalizedMessage();
                Toast.makeText(QuestionActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
