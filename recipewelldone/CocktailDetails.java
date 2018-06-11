package com.recipewelldone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.recipewelldone.parser.jsonCocktailDetails.CocktailDetailsMain;
import com.recipewelldone.parser.jsonCocktailIngrdient.Binding;
import com.recipewelldone.parser.jsonCocktailIngrdient.CocktailIngredient;
import com.squareup.picasso.Picasso;


public class CocktailDetails extends AppCompatActivity {

    ImageView headerImageView;
    TextView preparedAs;
    TextView ingredientTV;
    String name=new String();
    String img=new String();
    String uri=new String();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        headerImageView = (ImageView) findViewById(R.id.headerImageView);
        preparedAs = (TextView)findViewById(R.id.preparedAs);
        ingredientTV = (TextView) findViewById(R.id.ingredient);
        name =  getIntent().getStringExtra("name");
        img =  getIntent().getStringExtra("img");
        uri =  getIntent().getStringExtra("uri");
        Log.e("uri", uri);

        setSupportActionBar(toolbar);
        Picasso.with(getApplicationContext()).load(img).into(headerImageView);


        String url = getApplicationContext().getResources().getString(R.string.mainurl);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);

                Gson gson = new Gson();
                CocktailIngredient cocktailIngredient = gson.fromJson(response, CocktailIngredient.class);
                String ingredient = "";
                for(Binding binding : cocktailIngredient.getResults().getBindings()){
                    ingredient += binding.getIsPreparedWith().getValue() +"\n";
                }
                ingredientTV.setText(ingredient);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                String cocktailIngredientQuery = "" +
                        "  PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>\n" +
                        "\n" +
                        "  SELECT Distinct ?isPreparedWith\n" +
                        "  WHERE {\n" +
                        "   <http://127.0.0.1:3333/12564>  semantic:isPreparedWith ?isPreparedWith .\n" +
                        "  \t\n" +
                        "  }";

                cocktailIngredientQuery = cocktailIngredientQuery.replace("http://127.0.0.1:3333/12564", uri);


                Map<String, String> mParams = new HashMap<String, String>();

                mParams.put("query", cocktailIngredientQuery);
                mParams.put("output","json");
                return mParams;
            }

        };

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);
                Gson gson = new Gson();
                CocktailDetailsMain cocktailDetailsMain = gson.fromJson(response, CocktailDetailsMain.class);
                if(! TextUtils.isEmpty(cocktailDetailsMain.getResults().getBindings().get(0).getIsPreparedAs().getValue())){
                    preparedAs.setText(cocktailDetailsMain.getResults().getBindings().get(0).getIsPreparedAs().getValue());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> mParams = new HashMap<String, String>();
                String cocktailDetailQuery =
                        "  PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>\n" +
                                "\n" +
                                "  SELECT Distinct ?hasDrinkName ?belongsToDrinkCategory ?drinkHasImage ?isPreparedAs\n" +
                                "  WHERE {\n" +
                                "   <http://127.0.0.1:3333/12564>  semantic:hasDrinkName ?hasDrinkName.\n" +
                                "   <http://127.0.0.1:3333/12564>  semantic:belongsToDrinkCategory ?belongsToDrinkCategory.\n" +
                                "   <http://127.0.0.1:3333/12564>  <http://127.0.0.1:3333/drinkHasImage> ?drinkHasImage.\n" +
                                "   <http://127.0.0.1:3333/12564>  semantic:isPreparedAs ?isPreparedAs .\n" +
                                "  }";



                cocktailDetailQuery = cocktailDetailQuery.replace("http://127.0.0.1:3333/12564", uri);

                mParams.put("query", cocktailDetailQuery);
                mParams.put("output","json");

                return mParams;
            }

        };

        ReceipeApplication.getInstance().addToRequestQueue(stringRequest);
        ReceipeApplication.getInstance().addToRequestQueue(stringRequest2);

    }
}