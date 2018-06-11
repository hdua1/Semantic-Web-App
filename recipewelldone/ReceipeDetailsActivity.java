package com.recipewelldone;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.recipewelldone.parser.jsonCocktailDetails.CocktailDetailsMain;
import com.recipewelldone.parser.jsonCocktailIngrdient.Binding;
import com.recipewelldone.parser.jsonCocktailIngrdient.CocktailIngredient;
import com.recipewelldone.parser.jsonParserReceipe.ReceipeDetailsParser;
import com.recipewelldone.parser.jsonReceipeIngredient.ReceipeIngredient;
import com.recipewelldone.parser.jsonRestuarantInfo.RestuarantInfoMain;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

//import com.example.edplus.receipe_well_done.R;

public class ReceipeDetailsActivity extends AppCompatActivity {


    TextView ingredientTV;
    TextView instructionsTV;
    TextView cookingTimeTV;
    TextView isRatedAsTV;
    TextView belongsToCourseTV;
    TextView restuarantTV;
    TextView nutrientsTV;
    ImageView headerImageView;
    String name=new String();
    String img=new String();
    String uri=new String();

    public void initView(){
        ingredientTV = (TextView)findViewById(R.id.ingredientTV);
        instructionsTV = (TextView)findViewById(R.id.instructionsTV);
        cookingTimeTV = (TextView)findViewById(R.id.cookingTimeTV);
        isRatedAsTV = (TextView)findViewById(R.id.isRatedAsTV);
        belongsToCourseTV = (TextView)findViewById(R.id.belongsToCourseTV);
        headerImageView = (ImageView)findViewById(R.id.headerImageView);
        restuarantTV = (TextView)findViewById(R.id.restuarantTV);
        nutrientsTV = (TextView)findViewById(R.id.nutrientsTV);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(ReceipeApplication.counter%4 == 0)
            nutrientsTV.setText(R.string.nutrition1);
        if(ReceipeApplication.counter%4 == 1)
            nutrientsTV.setText(R.string.nutrition2);
        if(ReceipeApplication.counter%4 == 2)
            nutrientsTV.setText(R.string.nutrition3);
        if(ReceipeApplication.counter%4 == 3)
            nutrientsTV.setText(R.string.nutrition4);

        ReceipeApplication.counter ++;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipe_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        name =  getIntent().getStringExtra("name");
        img =  getIntent().getStringExtra("img");
        uri =  getIntent().getStringExtra("uri");

        //Log.e("urli", uri);
        String url = getApplicationContext().getResources().getString(R.string.mainurl);

        Picasso.with(getApplicationContext()).load(img).into(headerImageView);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.e("Response", response);
                Gson gson = new Gson();
                ReceipeDetailsParser receipeDetailsParser = gson.fromJson(response, ReceipeDetailsParser.class);

                if(receipeDetailsParser.getResults().getBindings() == null){
                    return;
                }
                com.recipewelldone.parser.jsonParserReceipe.Binding binding = receipeDetailsParser.getResults().getBindings().get(0);

                if(binding == null){
                    return;
                }

                if(binding.getHasCookingTime() != null && !TextUtils.isEmpty(binding.getHasCookingTime().getValue()))
                cookingTimeTV.setText("Cooking Time - " + binding.getHasCookingTime().getValue());

                if(binding.getIsRatedAs() != null && !TextUtils.isEmpty(binding.getIsRatedAs().getValue()))
                    isRatedAsTV.setText("Ratings - " + binding.getIsRatedAs().getValue());

                if(binding.getBelongsToCourse() != null && !TextUtils.isEmpty(binding.getBelongsToCourse().getValue()))
                    belongsToCourseTV.setText("Cuisine Type - "+binding.getBelongsToCourse().getValue());

                if(binding.getHasInstructions() != null &&!TextUtils.isEmpty(binding.getHasInstructions().getValue()))
                    instructionsTV.setText(binding.getHasInstructions().getValue());

                if(binding.getIsServedIn() != null && !TextUtils.isEmpty(binding.getIsServedIn  ().getValue()))
                    restuarantDetails(binding.getIsServedIn().getValue());
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

                String  receipeDetailsquery = "PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>\n" +
                        "SELECT Distinct ?hasDishName ?hasCookingTime ?hasflavourProfile ?isRatedAs ?hasIngredients ?belongsToCourse \n" +
                        "?belongsTo ?hasNutrient ?isServedIn ?hasInstructions ?url\n" +
                        "WHERE {\n" +
                        " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:hasDishName ?hasDishName.\n" +
                        " Optional { <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:hasCookingTime ?hasCookingTime.}\n" +
                        " Optional { <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:hasflavourProfile ?hasflavourProfile.}\n" +
                        " Optional { <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:isRatedAs ?isRatedAs .}\n" +
                        " Optional { <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:belongsToCourse ?belongsToCourse .} \n" +
                        " Optional { <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:belongsTo ?belongsTo.}\n" +
                        " Optional { <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:isServedIn ?isServedIn . }\n" +
                        " Optional { <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  <http://127.0.0.1:3333/hasInstructions> ?hasInstructions. }\n" +
                        " Optional { <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867> <http://127.0.0.1:3333/image>  ?url }.\n" +
                        "}";


                receipeDetailsquery = receipeDetailsquery.replace("http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867", uri);
                mParams.put("query", receipeDetailsquery);
                mParams.put("output","json");

                return mParams;
            }

        };



        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ReceipeIngredient receipeIngredient = gson.fromJson(response, ReceipeIngredient.class);
                String ingredient = "";
                for(com.recipewelldone.parser.jsonReceipeIngredient.Binding binding : receipeIngredient.getResults().getBindings()){
                    ingredient += binding.getHasIngredients().getValue() +", ";
                }

                if(TextUtils.isEmpty(ingredient)){
                    ingredient = "\"chicken cutlets\" , \"salt\" , \"curry powder\" , \"coconut oil\" , \"onions\" , \"golden raisins\" , \"ground black pepper\" , \"sour cream\" , \"toasted slivered almonds\" , \"fresh cilantro\"";
                }else{
                    ingredient = ingredient.substring(0,ingredient.length() - 2);
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
                Map<String, String> mParams = new HashMap<String, String>();

                String receipeingredientQuery = "\n" +
                        "PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>\n" +
                        "\n" +
                        "SELECT ?hasIngredients\n" +
                        "WHERE {\n" +
                        " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:hasIngredients ?hasIngredients.\n" +
                        "}";

                receipeingredientQuery = receipeingredientQuery.replace("http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867", uri);

                mParams.put("query", receipeingredientQuery);
                mParams.put("output","json");

                return mParams;
            }

        };


        ReceipeApplication.getInstance().addToRequestQueue(stringRequest);
        ReceipeApplication.getInstance().addToRequestQueue(stringRequest2);
    }



    public void restuarantDetails(final String isServedIn){
        String url = getApplicationContext().getResources().getString(R.string.mainurl);

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                String text = "";
                RestuarantInfoMain restuarantInfoMain = gson.fromJson(response, RestuarantInfoMain.class);

                com.recipewelldone.parser.jsonRestuarantInfo.Binding binding = restuarantInfoMain.getResults().getBindings().get(0);

                text += "Name - " + binding.getHasRestaurantName().getValue() + "\n";
                text += "Rating - " + binding.getHasRating().getValue() + "\n";
                text += "Cuisine - " + binding.getServesCuisine().getValue() + "\n";
                text += "Cost - " + binding.getCostRated().getValue() + "\n";
                text += "Address - " + binding.getIsLocatedAtPhysical().getValue() + "\n";
                text += "url - " + binding.getIsLocatedAtWeb().getValue() + "\n";



                if(!TextUtils.isEmpty(text)){
                    restuarantTV.setText(text);
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

                String restuarantInfoquery = "" +
                        "PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>\n" +
                        "SELECT ?hasRestaurantName ?isLocatedAtWeb ?isLocatedAtPhysical ?isLocatedInCity ?isLocatedAt ?servesCuisine ?costRated ?hasRating\n" +
                        "WHERE {\n" +
                        " <http://127.0.0.1:3333/1.7028613E7>  semantic:hasRestaurantName ?hasRestaurantName.\n" +
                        " <http://127.0.0.1:3333/1.7028613E7>  semantic:isLocatedAtWeb ?isLocatedAtWeb.\n" +
                        " <http://127.0.0.1:3333/1.7028613E7>  semantic:isLocatedAtPhysical ?isLocatedAtPhysical.\n" +
                        " <http://127.0.0.1:3333/1.7028613E7>  semantic:isLocatedInCity ?isLocatedInCity.\n" +
                        " <http://127.0.0.1:3333/1.7028613E7>  semantic:isLocatedAt ?isLocatedAt.\n" +
                        " <http://127.0.0.1:3333/1.7028613E7>  semantic:servesCuisine ?servesCuisine.\n" +
                        " <http://127.0.0.1:3333/1.7028613E7>  semantic:costRated ?costRated.\n" +
                        " <http://127.0.0.1:3333/1.7028613E7>  semantic:hasRating ?hasRating.\n" +
                        "  \n" +
                        "}";

                restuarantInfoquery = restuarantInfoquery.replace("1.7028613E7", isServedIn);
                mParams.put("query", restuarantInfoquery);
                mParams.put("output","json");

                return mParams;
            }

        };

        ReceipeApplication.getInstance().addToRequestQueue(stringRequest2);
    }

}
