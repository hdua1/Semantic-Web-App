package com.recipewelldone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cuisine1 extends AppCompatActivity {

    private ListView listView2;
    private GridView gridview2;
    String S= new String();
    CustomListAdapter adapter;
    ArrayList<card1> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine1);
        TextView text1=findViewById(R.id.text1);
        list = new ArrayList<>();
        S =  getIntent().getStringExtra("text");

        String url =  getApplicationContext().getResources().getString(R.string.mainurl);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.e("Response", response);
                ParseXML(response);
                adapter.notifyDataSetChanged();

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
                mParams.put("query", "PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>  SELECT Distinct ?Recipe ?hasDishName ?url WHERE { ?Recipe semantic:hasDishName ?hasDishName. ?Recipe <http://127.0.0.1:3333/image> ?url. ?Recipe semantic:belongsTo ?belongsTo. filter regex(?belongsTo,\""+S+"\")} LIMIT 25");
                return mParams;
            }

        };

        ReceipeApplication.getInstance().addToRequestQueue(stringRequest);

        text1.setText(S.toUpperCase()+" RECIPES");
        listView2 = (ListView)findViewById(R.id.list2);
        gridview2=(GridView)findViewById(R.id.grid2);
//        if(S.equals("AMERICAN")){
//            list.add(new card1("drawable://" + R.drawable.fries,"Fries"));
//            list.add(new card1("drawable://" + R.drawable.parmesancrispygrilledcheese,"Grilled Cheese Sandwich"));
//            list.add(new card1("drawable://" + R.drawable.chilimacandcheese,"Baked Macaroni And Cheese"));
//
//        }
//        else {
//            list.add(new card1(S + " RECIPE-1"));
//            list.add(new card1(S + " RECIPE-2"));
//            list.add(new card1(S + " RECIPE-3"));
//            list.add(new card1(S + " RECIPE-4"));
//            list.add(new card1(S + " RECIPE-5"));
//            list.add(new card1(S + " RECIPE-6"));
//            list.add(new card1(S + " RECIPE-7"));
//        }

        adapter = new CustomListAdapter(Cuisine1.this , R.layout.card_layout, list);
//        listView2.setAdapter(adapter);
//        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> a, View v, int position,
//                                    long id) {
//                String val= list.get(position).getTitle();
//                if(val.equals("Baked Macaroni And Cheese")) {
//                    Intent intent = new Intent(Cuisine1.this, ReceipeDetailsActivity.class);
//                    //intent.putExtra("text", list.get(position).getTitle());
//
//                    startActivity(intent);
//                }
//                else{
//                    Intent intent = new Intent(Cuisine1.this, CocktailDetails.class);
//                    //intent.putExtra("text", list.get(position).getTitle());
//
//                    startActivity(intent);
//                }
//            }
//        });
        gridview2.setAdapter(adapter);
        gridview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                String val= list.get(position).getTitle();
                Intent intent = new Intent(Cuisine1.this, ReceipeDetailsActivity.class);
                intent.putExtra("uri", list.get(position).getUri());
                intent.putExtra("name",list.get(position).getTitle());
                intent.putExtra("img", list.get(position).getImgURL());
                startActivity(intent);
            }
        });

    }

    public void ParseXML(String xmlString){

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));
            int eventType = parser.getEventType();
            int counter=0;
            String Name=new String();
            String Nam=new String();
            String uri= new String();
            while (eventType != XmlPullParser.END_DOCUMENT){

                if(eventType== XmlPullParser.START_TAG){

                    String name = parser.getName();
//                    if(name.equals("binding")){
//
//                        String ref = com.recipewelldone.parser.getAttributeValue(null,"name");
//                        //Log.e("ref:" , );
//                        if(ref.equals("DrinkName")){
//                            if(com.recipewelldone.parser.next()== XmlPullParser.START_TAG){
//                                String nam=com.recipewelldone.parser.getName();
//                                Log.e("nam",nam);
//                                if(com.recipewelldone.parser.next() == XmlPullParser.TEXT) {
//                                    String UpdateFlag = com.recipewelldone.parser.getText();
//                                    Log.e("UpdateFlag:" ,UpdateFlag);
//                                }
//                            }
//                        }

//                        if(com.recipewelldone.parser.next() == XmlPullParser.TEXT) {
//                            String UpdateFlag = com.recipewelldone.parser.getText();
//                            //Log.d(LOG_TAG,"UpdateFlag:" + UpdateFlag);
//                        }


//                    }
                    if(name.equals("literal")) {

                        if (parser.next() == XmlPullParser.TEXT) {
                            counter=counter+1;
                            if(counter==1){
                                Name = parser.getText();
                            }
                            else if(counter==2){
                                Nam=parser.getText();
                            }
                            //list.add(new card1(Name));
                            //Log.e("Name:" , Name);
                        }
//                    }else if(name.equals("Range")) {
//
//                        if(com.recipewelldone.parser.next() == XmlPullParser.TEXT) {
//                            String Range = com.recipewelldone.parser.getText();
//                            //Log.d(LOG_TAG,"Range:" + Range);
//                        }
//                    }
                    }

                    if(name.equals("uri")) {

                        if (parser.next() == XmlPullParser.TEXT) {
                            uri=parser.getText();
                            //list.add(new card1(Name));
                            //Log.e("Name:" , Name);
                        }
//                    }else if(name.equals("Range")) {
//
//                        if(com.recipewelldone.parser.next() == XmlPullParser.TEXT) {
//                            String Range = com.recipewelldone.parser.getText();
//                            //Log.d(LOG_TAG,"Range:" + Range);
//                        }
//                    }
                    }


                }else if(eventType== XmlPullParser.END_TAG){


                }
                if(counter==2){
                    list.add(new card1(Nam,Name,uri));
                    counter=0;
                }
                eventType = parser.next();

            }


        }catch (Exception e){
            Log.e("Error in ParseXML()",e.toString());
        }

    }
}

