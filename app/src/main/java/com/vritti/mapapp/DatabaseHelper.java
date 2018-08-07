package com.vritti.mapapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin-3 on 11/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HeritageMapDB";
    private static final int DATABASE_VERSION = 1;
    static final String PLACES_TABLE_NAME = "PlaceDetails";
    static final String place_name = "place_name";
    static final String vicinity = "vicinity";
    static final String type = "type";
    static final String info = "info";
    static final String lat = "lat";
    static final String lng = "lng";
    static final String reference = "reference";

    public static final String CREATE_PLACES_TABLE_NAME = "CREATE TABLE IF NOT EXISTS "

            + PLACES_TABLE_NAME
            + "(place_name TEXT,"+
            " vicinity TEXT,"+
            " type TEXT,"+
            " info TEXT,"+
            " lat TEXT,"+
            " lng TEXT,"+
            " reference TEXT)";

    public static final String HERITAGE_PLACE_VALUES ="Insert into "+ PLACES_TABLE_NAME+ " (" + place_name + ", "
            + vicinity + ", " + type + ", " + info + ", " + lat + ", " + lng + ", "+ reference + ") " +
            "values " +
            "('Marathi Vishwakosh Center'," +
            "'Gangapuri Road, Gangapuri, Wai'," +
            "'Office'," +
            "'The encyclopedia started as a print project and was inaugurated in 1960, and Lakshman Shastri Joshi was named the first president of the project. The first volumes were published in 1976, and eventually 18 volumes were published by 2010.'," +
            "'17.954115','73.884196'," +
            "'https://marathivishwakosh.maharashtra.gov.in/')," +

            "('Reshim Kendra Wai'," +
            "'Sidhanathwadi Rural, Maharashtra'," +
            "'Naturespoint'," +
            "'No info'," +
            "'17.933269','73.869742'," +
            "'WIP')," +

            "('Jadhavgadh park'," +
            "'Pasarni, Maharashtra'," +
            "'Naturespoint'," +
            "'No info'," +
            "'17.933209','73.83698'," +
            "'WIP')," +

            "('Table Land'," +
            "'Table Land Road, Godavali, Panchgani, Maharashtra'," +
            "'Naturespoint'," +
            "'No info'," +
            "'17.92642','73.807352'," +
            "'WIP')," +


            "('Venna Lake'," +
            "'Venna Lake, Mahabaleshwar, Maharashtra'," +
            "'WaterSports'," +
            "'No info'," +
            "'17.934759','73.66796'," +
            "'WIP')," +

            "('Pratap Singh Park'," +
            "'Panchgani-Mahabaleshwar Road, Mahabaleshwar, Maharashtra'," +
            "'Park'," +
            "'No info'," +
            "'17.932209','73.667052'," +
            "'WIP')," +

            "('Venna Dam'," +
            "'Panchgani-Mahabaleshwar Road, Mahabaleshwar, Maharashtra'," +
            "'Dam'," +
            "'No info'," +
            "'17.935316','73.668942'," +
            "'WIP')," +


            "('Jama Masjid'," +
            "'Rawiwar Peth, Raviwar Peth, Wai'," +
            "'Mosque'," +
            "'No info'," +
            "'17.951943','73.894878'," +
            "'WIP')," +

            "('Sunni Masjid'," +
            "'Sunni Masjid Wai, Gujarali Rd, Gujar Ali, Mathurapuri, Wai'," +
            "'Mosque'," +
            "'No info'," +
            "'17.950377','73.897633'," +
            "'WIP')," +

            /*"('Govrenment Hospital','Brahmanshahi, Wai, Maharashtra','Hospital','No info'" +
            ",'17.953562','73.890293','WIP')," +
            "('W. F. P. M. Hospital','WAI PANCHGANI ROAD, Wai-Panchgani Rd, Dhom Colony, Wai, Maharashtra','Hospital'" +
            ",'W. F. P. M. Hospital (Willis F Pierce Memorial) aka Mission Hospital'" +
            ",'17.947166','73.887118','WIP')," +*/

            "('St. Thomas Church'," +
            "'St. Thomas Church, Siddhanathwadi, Wai, Maharashtra'," +
            "'Church'," +
            "'No info'," +
            "'17.950883','73.885505'," +
            "'WIP')," +

            "('Sibley Memorial Church'," +
            "'Siddhanathwadi, Wai, Maharashtra'," +
            "'Church'," +
            "'Sibley Memorial Church Wai was established in 1916.'," +
            "'17.950893','73.885535'," +
            "'http://www.smchurchwai.com')," +

            "('Tilak Library'," +
            "'Madhali Ali, Ganpati Ali, Danebazar, Ganpati Ali, Wai, Maharashtra'," +
            "'Library'," +
            "'No info'," +
            "'17.954076','73.887177'," +
            "'WIP')," +


            "('Rutu Agri Farm'," +
            "' Wai, Dhom Dam Road, Satara, Maharashtra'," +
            "'Food'," +
            "'Idyllic setting at the foot hills of Panchgani. Only 4 kms from Wai, 1.5 hrs from Pune'," +
            "'17.963372','73.847255'," +
            "'http://www.rutufarm.com/')," +

            "('Bandu Gore Khanawal'," +
            "'Madhali Ali, Ganpati Ali, Danebazar, Ganpati Ali, Wai, Maharashtra'," +
            "'Food'," +
            "'Bandu Gore Bhojnalay at Wai makes sure one has a great food experience by offering highly palatable food. The restaurant welcomes guests from 10:00 - 22:00 allowing diners to relish a scrumptious meal between the functional hours. Near maha ganapati mandir,596,Datta Niwas,Ganpati Ali,Wai-412803 is where one can visit the venue. Courtesy to this strategic location, foodies in and around the neighborhood can walk in to this eating house conveniently without facing any hassles related to commuting to this part of the city. It is one of the most sought after Thali Restaurants in Wai. '," +
            "'17.953357','73.886832'," +
            "'https://foursquare.com/v/bandu-gore-bhojanalay/4eba3b29b8f765de72041472')," +

            "('Chaturthi Pure Veg Restaurant'," +
            "'Wai Satara Road, Sahyadri Nagar, Songirwadi Rural Sahyadri Nagar, Wai, Maharashtra'," +
            "'Food'," +
            "'No info'," +
            "'17.939406','73.89501'," +
            "'WIP')," +
/*
            "('New Chitra Talkies','New Chitra Talkies, Chota Pul, Gangapuri Road, Danebazar, Ganpati Ali, Wai, Maharashtra','Movie','No info'," +
            "'17.952462','73.887249','WIP')," +*/

            "('Gowardhan Sanstha Krishna Mandir'," +
            "'Wai panchgani road, Siddhanathwadi, Wai, Maharashtra'," +
            "'Cowshed'," +
            "'Govardhan Sanstha ghat has Krishna Mandir.'," +
            "'17.947804','73.880652'," +
            "'WIP')," +


            "('Wai Nagar Palika'," +
            "'Mandai, Ganpati Ali, Wai, Maharashtra'," +
            "'Office'," +
            "'Wai is a Municipal Council city in district of Satara, Maharashtra. The Wai city is divided into 19 wards for which elections are held every 5 years. Wai Municipal Council has total administration over 8,117 houses to which it supplies basic amenities like water and sewerage. It is also authorize to build roads within Municipal Council limits and impose taxes on properties coming under its jurisdiction.'," +
            "'17.954828','73.887021'," +
            "'https://en.wikipedia.org/wiki/Wai,_Maharashtra')," +

            "('Gandhi Petrolium'," +
            "'Panchgani Road, Wai-Panchgani-Mahabaleshwar Road, Sidhanathwadi, Wai, Maharashtra'," +
            "'Fuel'," +
            "'No info'," +
            "'17.948957','73.881122'," +
            "'http://www.onefivenine.com/india/Listing/Town/petrolpumps/Satara/Wai')," +


            "('S.T. Stand'," +
            "'S.T. Stand Rd, Siddhanathwadi, Wai, Maharashtra'," +
            "'Bus Stand'," +
            "'No info'" +
            ",'17.949551','73.883728'," +
            "'http://www.urbansketchers.org/2016/09/the-town-of-temples.html')," +


            "('Kashi Vishweshwar Mandir'," +
            "'Near Maha Ganpati Mandir, Ganpati Ali, Wai'," +
            "'Temple'," +
            "'No info'," +
            "'17.952928','73.88617'," +
            "'https://sites.google.com/site/waipanchgani/temples-in-wai/Kashiwishweswar')," +

            "('Ganpati Mandir'," +
            "'Raviwar Peth, Wai'," +
            "'Temple'," +
            "'No info'," +
            "'17.9528643','73.893796'," +
            "'https://www.google.co.in/maps/place/Ganpati+Mandir/@17.9528643,73.893796,3a,75y,90t/data=!3m8!1e2!3m6!1sAF1QipO2Bb0RtqWI2b_0vmPZxYQ0lDh0Ajq9ynkvRPs7!2e10!3e12!6s')," +

            "('Maha Ganpati Temple'," +
            "'Ganpati Ali, Wai'," +
            "'Temple'," +
            "'This temple was built by Raste in the 18th century beside the Krishna River. Dholya Ganpati temple is one of Maharashtras prime temples. The large idol of Lord Ganesh is on Krishna Rivers ghats.'" +
            ",'17.9525794','73.88622629999999'," +
            "'https://sites.google.com/site/waipanchgani/temples-in-wai/dholya-ganapati-temple')," +

            "('Uma Maheshwar Temple And Mangal Karyalay.'," +
            "'Gangapuri, Wai'," +
            "'Temple'," +
            "'No info'" +
            ",'17.9544105','73.8829993'," +
            "'https://sites.google.com/site/waipanchgani/temples-in-wai/UmamMaheswar-temple')," +

            "('Govind Rameshwar Mandir'," +
            "'Danebazar, Ganpati Ali, Wai'," +
            "'Temple'," +
            "'काशीविश्वेश्वर मंदिराच्या नगारखान्यासमोरच सुमारे तीसपस्तीस मीटरवर (शंभर - सव्वाशे फुटांवर) गोविंद –रामेश्वराचे पूर्वाभिमुख मंदिर आहे .यावर ते  सुस्थितीत  असताना  सुमारे ३२ मूर्ती  होत्या  आणि   काही  वर्षापूर्वीपर्यंत(२००३-२००४) त्या कशाबशा तग  धरून  होत्या ;पण अलीकडे नूतनीकरण व रंगकाम या प्रक्रियेत यांतील बहुसंख्य  ठिसूळ झाल्यामुळे काढून टाकण्यात आल्या आहेत; पण त्या मूर्तीतील पूर्वाभिमुख मधोमध एक गजाननाची सुरेख मूर्ती व त्याजवळची आणखी एक मूर्ती अद्याप अवशिष्ट असून त्यांतून उर्वरित  मूर्तींच्या घडणीची कल्पना येते .शिवाय इतर मंदिरांवरील प्रतिमानांत आढळणांर्या सरदार ,सैनिक, संगीतकार इत्यादीच्या काही मूर्तीबरोबर या मंदिराच्या शिखरांवरील सरस्वतीदेवी व एक सैनिक या दोन मूर्ती विशेष लक्षवेधकं व उल्लेखनीय होत्या .गजाननाच्या उजव्या बाजूस सरस्वतीची मूर्ती आग्नेयेकडे तोंड केलेली ,मोरावर बसलेली चतुर्भुज असून तिने आपल्या पुढच्या दोन हातांत वीणा धारण केला आहे. या tठिकाणी मोर फारसा नैसर्गिक वाटत नाही .तिने नक्षीदार कर्णफुले घातली असून तिची कंकणे मोत्याचीच आहेत .पायात पैजण व गळ्यात लांब रत्नहार आहे.तिच्या मस्तकावर मुकुट नाही;तर साधी भांग पाडलेली केशभूशा दिसते.तिचे मागचे दोन हात बाहूपाशी तुटले असले,तरी तिच्या हाताच्या तळव्यात अनुक्रमे पुस्तक व अक्षमाला आहे .देवीच्या मागे प्रभावळीसारखा मोराचा पिसारा पसरला आहे .काशीविश्वेश्वरातील मूर्तीप्रमाणेच तिची घडण होती .\n" +
            "याच मंदिराच्या शिखरावरील ईशान्य कोपर्यातील सर्वात वरच्या कोनाड्यात एका सैनिकाची त्वेषपूर्ण आविर्भावातील मूर्ती असून त्याच्या डाव्या हातात ढाल आहे आणि उजव्या हातात तलवारीसारखे शत्र आहे; परंतु उजवा हात कोपरापासून खांद्यापर्यत तुटलेला आहे .त्यामुळे तलवार अस्पष्ट दिसते.त्याचा प्रवित्रा मात्र सैनिकाचा त्वेष आणि लढण्यासाठी सज्ज झालेला आविर्भाव  दर्शवितो.त्याने पेशवेकालीन पगडी घातली असून ती मुकुटसदृश आहे.त्याचे उतरीय वस्त्र बाराबंदी अंगरख्यासारखे असून अधोवस्त्र धोतराप्रमाणे घोळदार आहे; कारण त्याच्या तीन चुण्या स्पष्ट दिसतात. त्याने कानांत भिकबाळी घातली असून त्याच्या चेहर्यावरून तो अगदी विशीच्या आतील तरूण असावा;तथापि या सर्व मूर्ती आता काळाच्या पडद्यामागे गेल्या आहेत.'," +
            "'17.9529805','73.8869158'," +
            "'https://sites.google.com/site/waipanchgani/temples-in-wai/rameshwar-temple')," +

            "('Shriram Mandir Dharmapuri','Dharmapuri, Brahmanshahi, Wai','Temple','No info'" +
            ",'17.9531697','73.8885434','WIP')," +
            "('Krishnabai Mandir','Chota Pul, Gangapuri Road, Danebazar, Ganpati Ali, Wai','Temple','No info'" +
            ",'17.9519992','73.8880429','WIP')" ;

    public static final String DROP_PLACES_TABLE_NAME = "DROP TABLE IF EXISTS " +PLACES_TABLE_NAME;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLACES_TABLE_NAME);
        db.execSQL(HERITAGE_PLACE_VALUES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
