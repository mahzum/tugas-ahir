package kelompok.uts;

/**
 * Created by Alip on 07/12/2017.
 */

public class Question {

    public String mQuestions[] = {
            /*01*/"Apakakah nama selat antara pulau Jawa dengan pulau sumatra?",
            /*02*/"Suku apa yang paling mendominasi di pulau papua?",
            /*03*/"Candi Borobudur berada di kabupaten?",
            /*04*/"Rumah adat joglo adalah rumah adat dari...",
            /*05*/"Danau toba terletak di provinsi?",
            /*06*/"Nama burung endemik di Papua adalah?",
            /*07*/"Orang utan berasal dari pulau",
            /*08*/"Komodo berasal dari pulau?",
            /*09*/"Badak bercula satu berasal dari daerah?",
            /*10*/"Babirusa berasal dari daerah?",
            /*11*/"SEA Games diadakan berapa tahun sekali?",
            /*12*/"Pada masa presiden siapakah indosat dijual?",
            /*13*/"Siapakah yang memproklamasikan kemerdekaan Indonesia?",
            /*14*/"Siapakah presiden di Indonesia yang menduduki jabatan terlama?",
            /*15*/"Siapakah bapak pendidikan nasional?",
            /*16*/"Hari pahlawan jatuh pada tanggal?",
            /*17*/"Tahun berapa Indonesia pernah keluar dari PBB?",
            /*18*/"Siapakah pemimpin G30 SPKI?",
            /*19*/"Apakah gambar yang terdapat pada lambang komunis?",
            /*20*/"Siapakah Kapolri yang terkenal paling jujur di Indonesia?",
            /*21*/"Apakah nama kerajaan pertama di Indonesia?",
            /*22*/"Dimanakah Sunan Kalijogo lahir?",
            /*23*/"Tahun berapa Cut Nyak Dien lahir?",
            /*24*/"Tahun berapa gunung Krakatau meletus?",
            /*25*/"Tahun berapa gunung Tambora meletus?"
    };

    private String mChoices[][] = {
            /*01*/{"Karimata", "Sunda", "Makasar", "Lombok"},
            /*02*/{"Jawa", "Madura", "Asmat", "Anak"},
            /*03*/{"Kendal", "Magelang", "Pati", "Blora"},
            /*04*/{"Jawa Tengah", "Sulawesi Utara", "NAD", "Jawa Timur"},
            /*05*/{"Sumatera Utara", "NTB", "Kalimantan Tengah", "DIY"},
            /*06*/{"Cendrawasih", "Kakatua", "Nuri", "Beo"},
            /*07*/{"Kalimantan", "Madura", "Natuna", "Jawa"},
            /*08*/{"Lombok", "NTB", "NTT", "Komodo", "Papua"},
            /*09*/{"Ujung Kulon", "Ujung Wetan", "Ujung Kidul", "Ujung Lor"},
            /*10*/{"Kalimantan", "Jawa", "Sulawesi", "Papua"},
            /*11*/{"1", "2", "3","4"},
            /*12*/{"Soekarno", "Soeharto", "Megawati", "SBY"},
            /*13*/{"Soekarno", "Soeharto", "Megawati", "SBY"},
            /*14*/{"Soekarno", "Soeharto", "Megawati", "SBY"},
            /*15*/{"Ki Hajar Dewantara", "Budi Utomo", "Budi Gunawan", "Ki Prana Lewu"},
            /*16*/{"10 Agustus", "10 September", "10 Oktober", "10 November"},
            /*17*/{"1962", "1963", "1964", "1965"},
            /*18*/{"Muso", "DN Aidit", "Amir Syarifuddin", "Nyoto"},
            /*19*/{"Palu dan Arit", "Palu dan Cangkul", "Palu dan Pedang", "Palu dan Padi"},
            /*20*/{"Tito", "Hoegeng", "Haiti", "Waseso", "Gunawan"},
            /*21*/{"Kutai", "Sriwijaya", "Salakanagara", "Kertanegara"},
            /*22*/{"Pati", "Semarang", "Nganjuk", "Tuban"},
            /*23*/{"1848", "1849", "1850", "1851"},
            /*24*/{"1880", "1881", "1882", "1883"},
            /*25*/{"1813", "1814", "1815", "1816"}

    };
    private String mCorrectAnswers[] = {
            /*01*/"Sunda",
            /*02*/"Asmat",
            /*03*/"Magelang",
            /*04*/"Jawa Tengah",
            /*05*/"Sumatera Utara",
            /*06*/"Cendrawasih",
            /*07*/"Kalimantan",
            /*08*/"Komodo",
            /*09*/"Ujung Kulon",
            /*10*/"Sulawesi",
            /*11*/"2",
            /*12*/"Megawati",
            /*13*/"Soekarno",
            /*14*/"Soeharto",
            /*15*/"Ki Hajar Dewantara",
            /*16*/"10 November",
            /*17*/"1965",
            /*18*/"DN Aidit",
            /*19*/"Palu dan Arit",
            /*20*/"Hoegeng",
            /*21*/"Salakanagara",
            /*22*/"Tuban",
            /*23*/"1848",
            /*24*/"1883",
            /*25*/"1815"

    };

    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }

    public String getChoice1(int a) {
        String choice = mChoices[a][0];
        return choice;
    }

    public String getChoice2(int a) {
        String choice = mChoices[a][1];
        return choice;
    }

    public String getChoice3(int a) {
        String choice = mChoices[a][2];
        return choice;
    }

    public String getChoice4(int a) {
        String choice = mChoices[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }
}
