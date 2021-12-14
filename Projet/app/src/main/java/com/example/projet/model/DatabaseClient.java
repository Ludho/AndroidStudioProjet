package com.example.projet.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projet.data.AppDatabase;

public class DatabaseClient {

    // Instance unique permettant de faire le lien avec la base de données
    private static DatabaseClient instance;

    // Objet représentant la base de données de votre application
    private AppDatabase appDatabase;

    // Constructeur
    private DatabaseClient(final Context context) {

        // Créer l'objet représentant la base de données de votre application
        // à l'aide du "Room database builder"
        // MyToDos est le nom de la base de données
        //appDatabase = Room.databaseBuilder(context, AppDatabase.class, "AppData").build();

        ////////// REMPLIR LA BD à la première création à l'aide de l'objet roomDatabaseCallback
        // Ajout de la méthode addCallback permettant de populate (remplir) la base de données à sa création
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "Data").addCallback(roomDatabaseCallback).build();
    }

    // Méthode statique
    // Retourne l'instance de l'objet DatabaseClient
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    // Retourne l'objet représentant la base de données de votre application
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    // Objet permettant de populate (remplir) la base de données à sa création
    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        // Called when the database is created for the first time.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Les pommes qu’elle a … sont trop mûres.\",\"acheté\",\"achetée\",\"achetés\",\"achetées\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Dans les chambres d’hôtel, il y a souvent des …\",\"coffre-fort\",\"coffre-forts\",\"coffres-fort\",\"coffres-forts\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Elle a acheté des rideaux …\",\"marrons\",\"turquoises\",\"oranges\",\"bleus\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Elle a acheté des rideaux …\",\"marrons\",\"turquoises\",\"oranges\",\"bleus\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"J’ai des cheveux …\",\"châtain clairs\",\"châtains clairs\",\"châtains clair\",\"châtain clair\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Je ne pense pas qu’il … malade.\",\"est\",\"a\",\"ait\",\"soit\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"La plupart des voitures …\",\"sont gris.\",\"est grises.\",\"est grise.\",\"sont grises.\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Quel nombre est mal écrit ?\",\"sept cents millions\",\"Six mille un\",\"Huit cent vingt\",\"Quatre-vingts-sept\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Quelle forme est correcte ?\",\"il est absent\",\"il sera absent\",\"il soit absent\",\"il serait absent\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Je vous … ce paquet de la part de Sophie.\",\"emmène\",\"emporte\",\"amène\",\"apporte\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"J’ai arrêté d’apprendre …, c’était trop difficile.\",\"l’Alemand\",\"l’Allemand\",\"l’alemand\",\"l’allemand\",\"francais\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Parmi ces villes, laquelle se situe le plus au Nord de la France ?\",\"Marseille\",\"Grenoble\",\"Bordeaux\",\"Lyon\",\"geographie\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Dans quelle région se trouve le Mont Saint-Michel ?\",\"En Bretagne\",\"Dans les Pays de La Loire\",\"En Île de France\",\"En Normandie\",\"geographie\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Quel département est le moins peuplé de France ?\",\"La Creuse\",\"Le Gers\",\"Le Cher\",\"La Lozère\",\"geographie\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Quel est le fleuve le plus long de France ?\",\"La Seine\",\"Le Rhône\",\"La Moselle\",\"La Loire\",\"geographie\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Depuis 2016, combien y a-t-il de régions en France métropolitaine ?\",\"5\",\"17\",\"24\",\"13\",\"geographie\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Lequel de ces sommets est le plus élevé ?\",\"Le mont d’Or\",\"Le mont Noir\",\"Le mont Joli\",\"Le mont Blanc\",\"geographie\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Combien de Pays ont une frontière avec la France métropolitaine ? \",\"6\",\"5\",\"7\",\"8\",\"geographie\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Laquelle de ces îles n’est pas française ?\",\"St-Pierre et Miquelon\",\"La Guadeloupe\",\"La Réunion\",\"La Barbade\",\"geographie\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Combien de mers et d’océans entourent la France métropolitaine?\",\"3\",\"5\",\"6\",\"4\",\"geographie\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"En 2018, combien y a-t-il d’habitants en France ?\",\"6.9 millions\",\"32 millions\",\"85,4 millions\",\"67.2 millions\",\"geographie\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Quand l’école est-elle devenue gratuite, laïque et obligatoire en primaire?\",\"2002\",\"1914\",\"1982\",\"1882\",\"histoire\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"A quelle occasion la tour Eiffel a-t-elle été construite?\",\"donner un symbole à la France\",\"montrer la puissance\",\"remercier le président\",\"une exposition universelle\",\"histoire\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Quelles sont les dates de la première guerre mondiale?\",\"1924-1928\",\"1939-1945\",\"1789-1815\",\"1914-1918\",\"histoire\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"En quelle année les femmes ont-elles eu le droit de vote?\",\"en 1945\",\"en 1918\",\"en 2000\",\"en 1944\",\"histoire\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Pendant quelle guerre le maréchal Pétain est-il devenu important pour la France?\",\"Pendant la révolution\",\"Seconde Guerre Mondiale\",\"Guerre franco-anglaise\",\"Première Guerre Mondiale\",\"histoire\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"En quelle année le mur de Berlin est-il détruit?\",\"en 1962\",\"en 1991\",\"en 1945\",\"en 1989\",\"histoire\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Qui est Winston Churchill?\",\"un homme politique français\",\"un homme politique americain\",\"un homme politique allemand\",\"un homme politique britannique\",\"histoire\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Qua écrit Victor Hugo?\",\"Le malade imaginaire\",\"Les fables de la Fontaine\",\"L’encyclopédie\",\"Les misérables\",\"histoire\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"En quelle année finit la seconde Guerre Mondiale?\",\"en 1991\",\"en 1918\",\"en 1951\",\"en 1945\",\"histoire\");");
            db.execSQL("INSERT INTO Question(question, reponse1, reponse2, reponse3, bonneReponse, theme) VALUES(\"Quel château symbolise la grandeur de Louis XIV?\",\"Saint Germain\",\"Le Louvre\",\"Lyon\",\"Versailles\",\"histoire\");");

        }
    };
}
