package com.recipewelldone;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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

public class searchResult extends AppCompatActivity {

    private ListView listView3;
    private GridView gridview3;
    String q= new String();
    final ArrayList<card1> list = new ArrayList<>();
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        TextView text1=findViewById(R.id.text1);
        q =  getIntent().getStringExtra("query string");

        final String url = getApplicationContext().getResources().getString(R.string.mainurl);
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
                //mParams.put("query", "PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>  SELECT Distinct ?hasDishName ?url WHERE { ?Recipe semantic:hasDishName ?hasDishName. ?Recipe <http://127.0.0.1:3333/image> ?url. filter regex(?hasDishName,\"" + q + "\",\"i\")} LIMIT 25");
                mParams.put("query", "PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>  SELECT Distinct ?Recipe ?hasDishName ?url ?hasDrinkname ?url1 WHERE { {?Recipe semantic:hasDishName ?hasDishName. ?Recipe <http://127.0.0.1:3333/image> ?url. filter regex(?hasDishName,\"" + q + "\",\"i\")}UNION{?Recipe semantic:hasDrinkName ?hasDrinkname. ?Recipe<http://127.0.0.1:3333/drinkHasImage> ?url1. filter regex(?hasDrinkname,\"" + q + "\",\"i\")}} LIMIT 50");
                return mParams;
            }

        };

        ReceipeApplication.getInstance().addToRequestQueue(stringRequest);

        text1.setText("SEARCH RESULT");
        //listView3 = (ListView)findViewById(R.id.list2);
        gridview3=(GridView)findViewById(R.id.grid3);
        //list.add(new card1("drawable://" + R.drawable.chilimacandcheese,"Baked Macaroni And Cheese"));
         adapter = new CustomListAdapter(searchResult.this , R.layout.card_layout, list);
//        listView3.setAdapter(adapter);
//        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> a, View v, int position,
//                                    long id) {
//                String val = list.get(position).getTitle();
//                Intent intent = new Intent(searchResult.this, ReceipeDetailsActivity.class);
//                //intent.putExtra("text", list.get(position).getTitle());
//
//                startActivity(intent);
//            }
//        });
        gridview3.setAdapter(adapter);
        gridview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                String x = list.get(position).getUri();
                String[] parts = x.split("/", 2);
                if (parts[1].matches(".*[a-z].*")) {
                    Intent intent = new Intent(searchResult.this, ReceipeDetailsActivity.class);
                    intent.putExtra("uri", list.get(position).getUri());
                    intent.putExtra("name", list.get(position).getTitle());
                    intent.putExtra("img", list.get(position).getImgURL());
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(searchResult.this, CocktailDetails.class);
                    intent.putExtra("uri", list.get(position).getUri());
                    intent.putExtra("name",list.get(position).getTitle());
                    intent.putExtra("img", list.get(position).getImgURL());
                    startActivity(intent);
                }
            }
        });

    }

    public void ParseXML(String xmlString) {

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));
            int eventType = parser.getEventType();
            int counter=0;
            String Name=new String();
            String Nam=new String();
            String Name1=new String();
            String Nam1=new String();
            String uri= new String();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG) {

                    String name = parser.getName();
//                    if(name.equals("UpdateFlag")){
//
//                        String ref = com.recipewelldone.parser.getAttributeValue(null,"ref");
//                        //Log.d(LOG_TAG,"ref:" + ref);
//
//                        if(com.recipewelldone.parser.next() == XmlPullParser.TEXT) {
//                            String UpdateFlag = com.recipewelldone.parser.getText();
//                            //Log.d(LOG_TAG,"UpdateFlag:" + UpdateFlag);
//                        }
//
//
//                    }
                    if (name.equals("literal")) {

                        if (parser.next() == XmlPullParser.TEXT) {
                            counter = counter + 1;
                            if (counter == 1) {
                                Name = parser.getText();
                            } else if (counter == 2) {
                                Nam = parser.getText();
                            } else if (counter == 3) {
                                Name1 = parser.getText();
                            } else if (counter == 4) {
                                Nam1 = parser.getText();
                            }
                        }

//                    }else if(name.equals("Range")) {
//
//                        if(com.recipewelldone.parser.next() == XmlPullParser.TEXT) {
//                            String Range = com.recipewelldone.parser.getText();
//                            //Log.d(LOG_TAG,"Range:" + Range);
//                        }
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


                } else if (eventType == XmlPullParser.END_TAG) {


                }
                if(counter==4){
                    list.add(new card1(Nam,Name,uri));
                    list.add(new card1(Nam1,Name1,uri));
//                    if(Name1!=""){
//                        val=false;
//                        list.add(new card1(Nam1,Name1,uri,val));
//                    }
                    counter=0;
                }
                eventType = parser.next();

            }


        } catch (Exception e) {
            Log.e("Error in ParseXML()", e.toString());
        }
    }

}
