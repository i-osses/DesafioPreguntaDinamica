package cl.desafiolatam.preguntadinamica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import cl.desafiolatam.preguntadinamica.api.Api;
import cl.desafiolatam.preguntadinamica.api.RetrofitClient;
import cl.desafiolatam.preguntadinamica.model.RespuestaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView question, category, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
//        question.setText("Hey yo");
        Api api = RetrofitClient.getRetrofit()
                .create(Api.class);

        Call<RespuestaApi> call =api.getQuestion();
        call.enqueue(new Callback<RespuestaApi>() {
            @Override
            public void onResponse(Call<RespuestaApi> call, Response<RespuestaApi> response) {

                difficulty.setText(response.body().getResults().get(0).getDifficulty());
                category.setText(response.body().getResults().get(0).getCategory());
                question.setText(response.body().getResults().get(0).getQuestion());
            }

            @Override
            public void onFailure(Call<RespuestaApi> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something's wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void initializeViews(){
        question = findViewById(R.id.question);
        category = findViewById(R.id.category);
        difficulty = findViewById(R.id.difficulty);
    }

}
