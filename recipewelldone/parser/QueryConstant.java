package com.recipewelldone.parser;

/**
 * Created by Yathartha on 12/3/17.
 */

public class QueryConstant {

    static String  receipeDetailsquery = "PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>\n" +
            "SELECT Distinct ?hasDishName ?hasCookingTime ?hasflavourProfile ?isRatedAs ?hasIngredients ?belongsToCourse \n" +
            "?belongsTo ?hasNutrient ?isServedIn ?hasInstructions ?url\n" +
            "WHERE {\n" +
            " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:hasDishName ?hasDishName.\n" +
            " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:hasCookingTime ?hasCookingTime.\n" +
            " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:hasflavourProfile ?hasflavourProfile.\n" +
            " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:isRatedAs ?isRatedAs .\n" +
            " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:belongsToCourse ?belongsToCourse . \n" +
            " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:belongsTo ?belongsTo.\n" +
            " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:isServedIn ?isServedIn .\n" +
            " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  <http://127.0.0.1:3333/hasInstructions> ?hasInstructions.\n" +
            " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867> <http://127.0.0.1:3333/image>  ?url .\n" +
            "}";



    static String restuarantInfoquery = "" +
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


    static String receipeingredientQuery = "\n" +
            "PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>\n" +
            "\n" +
            "SELECT ?hasIngredients\n" +
            "WHERE {\n" +
            " <http://127.0.0.1:3333/Revolutionary-Mac-_-Cheese-1048867>  semantic:hasIngredients ?hasIngredients.\n" +
            "}";


    public static String cocktailDetailQuery =
            "  PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>\n" +
                    "\n" +
                    "  SELECT Distinct ?hasDrinkName ?belongsToDrinkCategory ?drinkHasImage ?isPreparedAs\n" +
                    "  WHERE {\n" +
                    "   <http://127.0.0.1:3333/12564>  semantic:hasDrinkName ?hasDrinkName.\n" +
                    "   <http://127.0.0.1:3333/12564>  semantic:belongsToDrinkCategory ?belongsToDrinkCategory.\n" +
                    "   <http://127.0.0.1:3333/12564>  <http://127.0.0.1:3333/drinkHasImage> ?drinkHasImage.\n" +
                    "   <http://127.0.0.1:3333/12564>  semantic:isPreparedAs ?isPreparedAs .\n" +
                    "  }";


    public static  String cocktailIngredientQuery = "" +
            "  PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>\n" +
            "\n" +
            "  SELECT Distinct ?isPreparedWith\n" +
            "  WHERE {\n" +
            "   <http://127.0.0.1:3333/12564>  semantic:isPreparedWith ?isPreparedWith .\n" +
            "  \t\n" +
            "  }";

}