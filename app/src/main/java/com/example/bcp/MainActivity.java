package com.example.bcp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import android.telecom.Call;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    Button submitButton;
    TextView predictionResultTextView;
    private EditText Time;
    private EditText radiusMeanEditText;
    private EditText textureMeanEditText;
    private EditText smoothnessMeanEditText;
    private EditText compactnessMeanEditText;
    private EditText concavePointsMeanEditText;
    private EditText symmetryMeanEditText;
    private EditText fractalDimensionMeanEditText;
    private EditText textureSeEditText;
    private EditText perimeterSeEditText;
    private EditText smoothnessSeEditText;
    private EditText concavitySeEditText;
    private EditText concavePointsSeEditText;
    private EditText symmetrySeEditText;
    private EditText fractalDimensionSeEditText;
    private EditText smoothnessWorstEditText;
    private EditText compactnessWorstEditText;
    private EditText concavePointsWorstEditText;
    private EditText symmetryWorstEditText;
    private EditText tumorSizeEditText;
    private EditText lymphNodeStatusEditText;

    String url="http://girishh.pythonanywhere.com/predict";
    //String url = "http://http://127.0.0.1:5000/predict";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitButton=findViewById(R.id.submit_button);
        predictionResultTextView = findViewById(R.id.prediction_result);
        Time =findViewById(R.id.time_input);
        radiusMeanEditText = findViewById(R.id.radius_mean_input);
        textureMeanEditText = findViewById(R.id.texture_mean_input);
        smoothnessMeanEditText = findViewById(R.id.smoothness_mean_input);
        compactnessMeanEditText = findViewById(R.id.compactness_mean_input);
        concavePointsMeanEditText = findViewById(R.id.concave_points_mean_input);
        symmetryMeanEditText = findViewById(R.id.symmetry_mean_input);
        fractalDimensionMeanEditText = findViewById(R.id.fractal_dimension_mean_input);
        textureSeEditText = findViewById(R.id.texture_se_input);
        perimeterSeEditText = findViewById(R.id.perimeter_se_input);
        smoothnessSeEditText = findViewById(R.id.smoothness_se_input);
        concavitySeEditText = findViewById(R.id.concavity_se_input);
        concavePointsSeEditText = findViewById(R.id.concave_points_se_input);
        symmetrySeEditText = findViewById(R.id.symmetry_se_input);
        fractalDimensionSeEditText = findViewById(R.id.fractal_dimension_se_input);
        smoothnessWorstEditText = findViewById(R.id.smoothness_worst_input);
        compactnessWorstEditText = findViewById(R.id.compactness_worst_input);
        concavePointsWorstEditText = findViewById(R.id.concave_points_worst_input);
        symmetryWorstEditText = findViewById(R.id.symmetry_worst_input);
        tumorSizeEditText = findViewById(R.id.tumor_size_input);
        lymphNodeStatusEditText = findViewById(R.id.lymph_node_status_input);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hit the API -> volley
                //create an string request object,where it has four parameters(kind_of_request,url,listener,inputs)
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                               //  Create JSON object with input values

                                try {
                                    JSONObject jsonObject = new JSONObject();
                                    String data = jsonObject.getString("Cancer");
                                    if(data.equals("N")){
                                        predictionResultTextView.setText("Non-Occuring");
                                    }
                                    else{
                                        predictionResultTextView.setText("Occuring");
                                    }

                                 }
                                catch (JSONException e) {
                                   e.printStackTrace();
                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage()); Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                ){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params =new HashMap<String,String>();
                        params.put("Time",Time.getText().toString());
                        params.put("radius_mean",radiusMeanEditText.getText().toString());
                        params.put("texture_mean",textureMeanEditText.getText().toString());
                        params.put("smoothness_mean",smoothnessMeanEditText.getText().toString());
                        params.put("compactness_mean",compactnessMeanEditText.getText().toString());
                        params.put("concave_points_mean",concavePointsMeanEditText.getText().toString());
                        params.put("symmetry_mean",symmetryMeanEditText.getText().toString());
                        params.put("fractal_dimension_mean",fractalDimensionMeanEditText.getText().toString());
                        params.put("texture_se",textureSeEditText.getText().toString());
                        params.put("perimeter_se",perimeterSeEditText.getText().toString());
                        params.put("smoothness_se",smoothnessSeEditText.getText().toString());
                        params.put("concavity_se",concavitySeEditText.getText().toString());
                        params.put("concave_points_se",concavePointsSeEditText.getText().toString());
                        params.put("symmetry_se",symmetrySeEditText.getText().toString());
                        params.put("fractal_dimension_se",fractalDimensionSeEditText.getText().toString());
                        params.put("smoothness_worst",smoothnessWorstEditText.getText().toString());
                        params.put("compactness_worst",compactnessWorstEditText.getText().toString());
                        params.put("concave_points_Worst",concavePointsWorstEditText.getText().toString());
                        params.put("symmetry_worst",symmetryWorstEditText.getText().toString());
                        params.put("tumor_size",tumorSizeEditText.getText().toString());
                        params.put("lymph_node_status",lymphNodeStatusEditText.getText().toString());
                        return params;
                    }
                };
                RequestQueue queue=Volley.newRequestQueue(MainActivity.this);
                queue.add(stringRequest);

            }

        });

    }
}


//                // Get input values from EditText views
//               // String radiusMean = radiusMeanEditText.getText().toString().trim();
//                //String textureMean = textureMeanEditText.getText().toString().trim();
//                // ... (code omitted for brevity)
//
//                // Construct URL for Flask API endpoint
//                String url = "http://http://127.0.0.1:5000/predict";
//
//
//
//                // Send POST request to Flask API endpoint
//                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                // Handle successful response from Flask API
//                                try {
//                                    boolean prediction = response.getBoolean("prediction");
//                                    String result = prediction ? "Breast cancer will recur" : "Breast cancer will not recur";
//                                    resultTextView.setText(result);
//                                } catch (JSONException e) {
//                                    Log.e(TAG, "Error parsing response from Flask API", e);
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // Handle error response from Flask API
//                                Log.e(TAG, "Error making request to Flask API", error);
//                            }
//                        });
//                queue.add(request);