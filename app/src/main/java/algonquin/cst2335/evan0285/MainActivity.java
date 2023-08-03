package algonquin.cst2335.evan0285;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URLEncoder;

import algonquin.cst2335.evan0285.databinding.ActivityMainBinding;

/**
 * @author Chris Evans
 * @version 1.0
 * Class that checks password validity
 */
public class MainActivity extends AppCompatActivity {


    protected String cityName;
    protected RequestQueue queue = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(  binding.getRoot()  );
        binding.getForecast.setOnClickListener(click -> {
            cityName = binding.editText.getText().toString();
            String stringURL = "https://api.openweathermap.org/data/2.5/weather?q="
                    + URLEncoder.encode(cityName)
                    + "&appid=2c84169e5f2fcb3daa042006f47a5820&units=metric";


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {
                        try {
                            JSONObject coord = response.getJSONObject( "coord" );

                            JSONObject mainObject = response.getJSONObject( "main" );
                            double current = mainObject.getDouble("temp");
                            double min = mainObject.getDouble("temp_min");
                            double max = mainObject.getDouble("temp_max");
                            int humidity = mainObject.getInt("humidity");


                            JSONArray weatherArray = response.getJSONArray ( "weather" );


                            JSONObject position0 = weatherArray.getJSONObject(0);

                            String description = position0.getString("description");
                            String iconName = position0.getString("icon");
                            int vis = response.getInt("visibility");
                            String name = response.getString( "name" );

                            String imageUrl = "https://openweathermap.org/img/w/" + iconName + ".png";
                            ImageRequest imgReq = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap response) {
                                    binding.icon.setImageBitmap(response);
                                    binding.icon.setVisibility(View.VISIBLE);


                                }
                            }, 1024, 1024, ImageView.ScaleType.CENTER, null, (error ) -> {

                            });
//                            FileOutputStream fOut = null;
//                            try {
//                                fOut = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);
//
//                                image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//                                fOut.flush();
//                                fOut.close();
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//
//                            }
                            queue.add(imgReq);

                            runOnUiThread( (  )  -> {

                                binding.temp.setText("The current temperature is " + current);
                                binding.temp.setVisibility(View.VISIBLE);

                                binding.minTemp.setText("The min temperature is " + min);
                                binding.minTemp.setVisibility(View.VISIBLE);

                                binding.maxTemp.setText("The max temperature is " + max);
                                binding.maxTemp.setVisibility(View.VISIBLE);

                                binding.humidity.setText("The humidity is " + humidity);
                                binding.humidity.setVisibility(View.VISIBLE);

                                binding.visibility.setText("The visibility is " + vis);
                                binding.visibility.setVisibility(View.VISIBLE);

                                binding.minTemp.setText(description);
                                binding.minTemp.setVisibility(View.VISIBLE);


//                                binding.icon.setImageBitmap();
//                                binding.icon.setVisibility(View.VISIBLE);






                                // do this for all the textViews...

                            });


                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    },
                    (error) -> {int i = 0;});
            queue.add(request);

        });


    }
}
