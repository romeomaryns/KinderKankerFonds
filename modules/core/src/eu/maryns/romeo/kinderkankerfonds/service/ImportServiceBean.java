package eu.maryns.romeo.kinderkankerfonds.service;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.Metadata;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import eu.maryns.romeo.kinderkankerfonds.entity.*;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(ImportService.NAME)
public class ImportServiceBean implements ImportService {


    private SimpleDateFormat parser=new SimpleDateFormat("DD/MM/YYYY");
    private SimpleDateFormat longParser=new SimpleDateFormat("YYYYMMDDHHmm");
    private final String user = "Import";

    @Inject
    private Persistence persistence;

    @Inject
    private Metadata metadata;

    @Override
    public void importCSV(File file) throws IOException {
        System.out.println("inside service file = "  + file.getAbsolutePath());
        System.out.println("inside service size = "  + file.getAbsoluteFile().length());
        Reader reader = Files.newBufferedReader(file.toPath(),Charset.forName("cp1252"));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        String[] nextRecord;
        int counter = 0;
            while ((nextRecord = csvReader.readNext()) != null) {
                try{
                    String naam = nextRecord[0];
                    String naamAlias = nextRecord[1];
                    String straat = nextRecord[3];
                    String postnummer = nextRecord[4];
                    String stad = nextRecord[5];
                    String telefoon = nextRecord[6];
                    String contactDatum = nextRecord[7];
                    String opnameDatum = nextRecord[8];
                    String vpkdos = nextRecord[9];
                    String diagnose =  nextRecord[10];
                    String ftk = nextRecord[12] ;
                    String overlijden = nextRecord[13];
                    String defpsy = nextRecord[18];
                    String tutor = nextRecord[21];
                    String geboorteDatum = nextRecord[26];
                    String plover = nextRecord[27];
                    String patient = nextRecord[29] ;
                    String uzgent = nextRecord[30] ;
                    String sex = nextRecord[31] ;
                    String koester = nextRecord[32] ;
                    String ispostrans = nextRecord[33] ;
                    String mailing = nextRecord[34] ;
                    String uniekeid = nextRecord[35];
               //     System.out.println("Unieke id = " + uniekeid);
                    String updateTijd = nextRecord[37];
                    String deleteTijd = nextRecord[38];
                    Persoon persoon = null;
                    try (Transaction tx = persistence.createTransaction()) {
                        persoon = findPersoonByUniekeId(uniekeid);
                        if(null == persoon ){ persoon = findPersoonByFamilienaam(naam);}
                        if(null == persoon ){persoon = maakPersoon(uniekeid);}
                        persoon.setFamilienaam(naam);
                        persoon.setVoornaam(naamAlias);
                        try {
                            if (sex.equals("1")) {
                                persoon.setGeslacht(getGeslacht("Man"));
                            } else {
                                persoon.setGeslacht(getGeslacht("Vrouw"));
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        try {
                            persoon.setOverlijdensdatum(parser.parse(overlijden));
                        }catch(Exception e)
                        {
                         //   System.out.println("Error in parsen overlijdensdatum : " + overlijden);
                        }
                        try
                        {
                            persoon.setUpdateTs(longParser.parse(updateTijd));
                        }catch(Exception e)
                        {
                          //  System.out.println("Error in parsen updateTijd : " + updateTijd);
                        }
                        persoon.setUpdatedBy(user);
                        try
                        {
                           // persoon.setDeleteTs(longParser.parse(deleteTijd));
                        }catch(Exception e)
                        {
                        //    System.out.println("Error in parsen deleteTijd : " + deleteTijd);
                        }
                        persoon.setDeletedBy(user);
                        try
                        {
                            persoon.setGeboortedatum(parser.parse(geboorteDatum));
                        }catch(Exception e)
                        {
                       //     System.out.println("Error in parsen geboortedatum : " + geboorteDatum);
                        }
                        Adres adres =maakAdres(straat,postnummer,stad,uniekeid);
                        adres.setPersoon(persoon);
                        List<Adres> adressen = persoon.getAdressen();
                        if(adressen == null)
                        {
                            adressen = new ArrayList<Adres>();
                            adressen.add(adres);
                            persoon.setAdressen(adressen,persoon);
                        }
                        else{
                            persoon.getAdressen().add(adres);
                        }
                       persoon.setActief(true);
                        StringBuilder sb = new StringBuilder("Geïmporteerde Data:\n=====================\n")
                        .append("NAAM : " ).append(naam).append("\n")
                        .append("NAAMALIAS : " ).append( naamAlias).append("\n")
                        .append("ADNR : " ).append( nextRecord[2]).append("\n")
                        .append("STR : " ).append( straat).append("\n")
                        .append("PSTNR : ").append(postnummer).append("\n")
                        .append("GEM : ").append(stad).append("\n")
                        .append("TEL : ").append(telefoon).append("\n")
                        .append("CONTDAT : ").append(contactDatum).append("\n")
                        .append("OPNDAT : ").append(opnameDatum).append("\n")
                        .append("VPKDOS : ").append(vpkdos).append("\n")
                        .append("DIAG : ").append(diagnose).append("\n")
                        .append("FNR : ").append(nextRecord[11]).append("\n")
                        .append("FTK : ").append(ftk).append("\n")
                        .append("OVERL : ").append(overlijden).append("\n")
                        .append("DR : ").append(nextRecord[14]).append("\n")
                        .append("MEMO : ").append(nextRecord[15]).append("\n")
                        .append("OKPAT : ").append(nextRecord[16]).append("\n")
                        .append("PSYID : ").append(nextRecord[17]).append("\n")
                        .append("DEFPSY : ").append(defpsy).append("\n")
                        .append("TUTORID : ").append(nextRecord[19]).append("\n")
                        .append("SOCID : ").append(nextRecord[20]).append("\n")
                        .append("TUTOR : ").append(tutor).append("\n")
                        .append("UITDAT : ").append(nextRecord[22]).append("\n")
                        .append("KSDIAG : ").append(nextRecord[23]).append("\n")
                        .append("DATCUR : ").append(nextRecord[24]).append("\n")
                        .append("DATPAL : ").append(nextRecord[25]).append("\n")
                        .append("GEBDAT : ").append(geboorteDatum).append("\n")
                        .append("PLOVER : ").append(plover).append("\n")
                        .append("KSMEMO : ").append(nextRecord[28]).append("\n")
                        .append("PATIENT : ").append(patient).append("\n")
                        .append("UZGENT : " +uzgent).append("\n")
                        .append("SEX : ").append(sex).append("\n")
                        .append("KOESTER : ").append(koester).append("\n")
                        .append("ISPOSTRANS : ").append(ispostrans).append("\n")
                        .append("MAILING : ").append(mailing).append("\n")
                        .append("UNIEKID : ").append(uniekeid).append("\n")
                        .append("UPD : ").append(nextRecord[36]).append("\n")
                        .append("UPDTIJD : ").append(updateTijd).append("\n")
                        .append("SUPDTIJD : ").append(deleteTijd).append("\n")
                        .append("RIJKSREG : ").append(nextRecord[39]).append("\n")
                        .append("==========================");
                        Notitie notitie =maakNotitie(sb.toString());
                        notitie.setPersoon(persoon);
                        List<Notitie> notities = persoon.getNotities();
                        if(null == notities){
                            notities = new ArrayList<Notitie>();
                            notities.add(notitie);
                            persoon.setNotities(notities);
                        }
                        else
                        {
                            notities.add(notitie);
                        }
                        ContactInfo contactInfo1 = null;
                        if(null != telefoon && telefoon.length() > 0) {
                            contactInfo1 = maakContactInfo(telefoon);
                            contactInfo1.setPersoon(persoon);
                        }
                        List<ContactInfo> contactInfo = persoon.getContactinfo();
                        if(null != contactInfo1)
                        {
                            if(null == contactInfo) {
                                contactInfo = new ArrayList<ContactInfo>();
                                contactInfo.add(contactInfo1);
                                persoon.setContactinfo(contactInfo);
                            }
                            else{
                                contactInfo.add(contactInfo1);
                            }
                        }
                        counter++;
                        System.out.println(counter + "   Records verwerkt ! inhoud : " + sb.toString());
                        persistence.getEntityManager().merge(persoon);
                        tx.commit();
                    }
                catch(Exception e)
                {
                    System.out.println("fout tijdens record");
                    e.printStackTrace();
                }
                }catch(Exception e){e.printStackTrace();}
            }
    }

    public void importAdres(File file) throws IOException {
        System.out.println("inside service file = "  + file.getAbsolutePath());
        System.out.println("inside service size = "  + file.getAbsoluteFile().length());
        Reader reader = Files.newBufferedReader(file.toPath(),Charset.forName("cp1252"));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        String[] nextRecord;
        int counter = 0;
        int nulRecords = 0;
        int eenRecords = 0;
        int anderREcords = 0;
        int skipped = 0;
        while ((nextRecord = csvReader.readNext()) != null) {
            try {
                String recType = nextRecord[0];
                String dateOpen = nextRecord[1];
                String uniekeid = nextRecord[4];
           //     System.out.println("Unieke id = " + uniekeid);
                String geboorteDatum = nextRecord[5];
                String aanspreking = nextRecord[6];
                String naam = nextRecord[7];
                String contact1 = nextRecord[8];
                String contact2 = nextRecord[9];
                String voornaam = nextRecord[10];
                String sex = nextRecord[11];
                String straat = nextRecord[12];
                String stad = nextRecord[13];
                String postnummer = nextRecord[14];
                String land = nextRecord[15];
                String kkfmail = nextRecord[16];
                String email = nextRecord[17];
                String prefEmail = nextRecord[18];
                String adresOK = nextRecord[20];
                String rip = nextRecord[21];
                String dateCreate = nextRecord[22];
                String notitie = nextRecord[23];
                String telVast = nextRecord[24];
                String telMob = nextRecord[25];
                String adresType = nextRecord[26];
                String updateTijd = nextRecord[27];
                String deleteTijd = nextRecord[28];
                Persoon persoon = null;

                try (Transaction trans = persistence.createTransaction()) {
                  //  persoon = findPersoonByUniekeId(uniekeid);
                 //   if(null == persoon ){
                        persoon = findPersoonByVoorEnFamilienaam(voornaam,naam);
                    //}
                    if(null == persoon ){persoon = maakPersoon();}
                        switch (recType){
                            case "0" :
                                nulRecords++;
                                Categorie catego = getCategorie("Firma");
                                try {
                                    if (!catego.getPersonen().contains(persoon)) {
                                        catego.getPersonen().add(persoon);
                                    }
                                }catch(Exception e){System.out.println("geen categorie gevonden");}
                                List<Categorie> categorie = persoon.getCategorieen();
                                if(null == categorie){
                                    System.out.println("Categorie is null");
                                    categorie = new ArrayList<Categorie>();
                                    categorie.add(catego);
                                    persoon.setCategorieen(categorie);
                                }
                                else{
                                    if(categorie.contains(catego)){
                                        persoon.getCategorieen().add(catego);
                                    }
                                }
                                break;
                            case "1" :
                                eenRecords++;
                                try
                                {
                                    if(sex.equals("1"))
                                    {
                                        persoon.setGeslacht(getGeslacht("Man"));
                                    }
                                    else
                                    {
                                        persoon.setGeslacht(getGeslacht("Vrouw"));
                                    }
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                                break;
                            default:    anderREcords++;break;
                        }
                        persoon.setFamilienaam(naam);
                        persoon.setVoornaam(voornaam);
                        try
                        {
                            persoon.setUpdateTs(longParser.parse(updateTijd));
                        }catch(Exception e)
                        {
                            //  System.out.println("Error in parsen updateTijd : " + updateTijd);
                        }
                        persoon.setUpdatedBy(user);
                        try
                        {
                           // persoon.setDeleteTs(longParser.parse(deleteTijd));
                        }catch(Exception e)
                        {
                            //    System.out.println("Error in parsen deleteTijd : " + deleteTijd);
                        }
                        persoon.setDeletedBy(user);
                        try
                        {
                            persoon.setGeboortedatum(parser.parse(geboorteDatum));
                        }catch(Exception e)
                        {
                            //     System.out.println("Error in parsen geboortedatum : " + geboorteDatum);
                        }
                        Adres adres =maakAdres(straat,postnummer,stad,uniekeid);
                        try{
                            adres.setPersoon(persoon);
                        }
                        catch(Exception e)
                            {System.out.println("Geen adressen gevonden");
                            e.printStackTrace();
                            }
                        List<Adres> adressen = persoon.getAdressen();
                        if(adressen == null)
                        {
                            adressen = new ArrayList<Adres>();
                            adressen.add(adres);
                            persoon.setAdressen(adressen,persoon);
                        }
                        else{
                            persoon.getAdressen().add(adres);
                        }
                        persoon.setActief(true);
                        StringBuilder sb = new StringBuilder("Geïmporteerde Data:\n=====================\n")
                                .append("RECTYPE : " ).append(recType).append("\n")
                                .append("DATOPN : " ).append( dateOpen).append("\n")
                                .append("DATAFSLUIT : " ).append( nextRecord[2]).append("\n")
                                .append("GEOZONE : " ).append( nextRecord[3]).append("\n")
                                .append("UNIEKID : ").append(uniekeid).append("\n")
                                .append("GEBDAT : ").append(geboorteDatum).append("\n")
                                .append("AANSPR : ").append(aanspreking).append("\n")
                                .append("NAAM : ").append(naam).append("\n")
                                .append("CTCPERS1 : ").append(contact1).append("\n")
                                .append("CTCPERS2 : ").append(contact2).append("\n")
                                .append("VOORNAAM : ").append(voornaam).append("\n")
                                .append("SEX : ").append(sex).append("\n")
                                .append("ADRES : ").append(straat).append("\n")
                                .append("WPL : ").append(stad).append("\n")
                                .append("POSTC : ").append(postnummer).append("\n")
                                .append("LAND : ").append(land).append("\n")
                                .append("KKFMAIL : ").append(kkfmail).append("\n")
                                .append("EMAIL : ").append(email).append("\n")
                                .append("PREFEMAIL : ").append(prefEmail).append("\n")
                                .append("ISPATIENT : ").append(nextRecord[19]).append("\n")
                                .append("ADRESOK : ").append(adresOK).append("\n")
                                .append("RIP : ").append(rip).append("\n")
                                .append("DATCREATIE : ").append(dateCreate).append("\n")
                                .append("INFO : ").append(notitie).append("\n")
                                .append("TELVAST : ").append(telVast).append("\n")
                                .append("TELMOB : ").append(telMob).append("\n")
                                .append("ADRESTYPE : ").append(adresType).append("\n")
                                .append("UPDTIJD : ").append(updateTijd).append("\n")
                                .append("SUPDTIJD : ").append(deleteTijd).append("\n")
                                .append("==========================");
                        Notitie notitie1 =maakNotitie(sb.toString());
                        notitie1.setPersoon(persoon);
                        List<Notitie> notities = persoon.getNotities();
                        if(null == notities){
                            notities = new ArrayList<Notitie>();
                            notities.add(notitie1);
                            persoon.setNotities(notities);
                        }
                        else
                        {
                            notities.add(notitie1);
                        }
                        ContactInfo contactInfo1 = null;
                        if(null != telMob || null != telVast || null != email) {
                            contactInfo1 = maakContactInfo(telVast,telMob,email);
                            contactInfo1.setPersoon(persoon);
                        }
                        List<ContactInfo> contactInfo = persoon.getContactinfo();
                        if(null != contactInfo1)
                        {
                            if(null == contactInfo) {
                                contactInfo = new ArrayList<ContactInfo>();
                                contactInfo.add(contactInfo1);
                                persoon.setContactinfo(contactInfo);
                            }
                            else{
                                contactInfo.add(contactInfo1);
                            }
                        }
                        persoon = persistence.getEntityManager().merge(persoon);
                        counter++;
                        System.out.println(counter + "   Records verwerkt ! " + persoon.getInstanceName());
                trans.commit();
                }
            }
            catch(Exception e)
            {
                System.out.println("fout tijdens record");
                e.printStackTrace();
            }
        }
        System.out.println("Aantal records : " + counter + " waarvan :\n"+nulRecords + " nulRecords\n"+eenRecords+" eenRecords\n" +anderREcords + " andere records\nEn skipped records : " + skipped);

    }

    @Override
    public void importKkfMail(File file) throws IOException {
        System.out.println("inside service file = "  + file.getAbsolutePath());
        System.out.println("inside service size = "  + file.getAbsoluteFile().length());
        Reader reader = Files.newBufferedReader(file.toPath(),Charset.forName("cp1252"));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        String[] nextRecord;
        int counter = 0;
        while ((nextRecord = csvReader.readNext()) != null) {
            try {
                String adresid = nextRecord[0];
             //   System.out.println("adres id = " + adresid);
                String mailcode = nextRecord[1];
                String vanafdatum = nextRecord[2];
                String totEnMetDatum = nextRecord[3];
                String uniekeid = nextRecord[6];
                String updateTijd = nextRecord[4];
                String deleteTijd = nextRecord[5];

            try (Transaction tx = persistence.createTransaction()) {
                Persoon persoon =null;
                persoon = findPersoonByAdresUniekeId(adresid);
               // if(null == persoon ){ persoon = findPersoonByUniekeId(uniekeid);}
                if(null != persoon ) {
                        Categorie catego = getCategorie(mailcode);
                        try {
                        //    if (!catego.getPersonen().contains(persoon)) {
                                catego.getPersonen().add(persoon);
                      //      }
                        }catch(Exception e){System.out.println("geen categorie gevonden");
                        List<Persoon> personen = new ArrayList<>();
                        personen.add(persoon);
                        catego.setPersonen(personen);
                        }
                        List<Categorie> categorie = persoon.getCategorieen();
                        if(null == categorie){
                            System.out.println("Categorie is null");
                            categorie = new ArrayList<Categorie>();
                            categorie.add(catego);
                            persoon.setCategorieen(categorie);
                        }
                        else{
  //                          if(!categorie.contains(catego)){
                                persoon.getCategorieen().add(catego);
//                            }
                        }
                        Date vanaf = null, tot = null;
                        switch (mailcode) {
                            case "RKP":
                                System.out.println("Raakpunt gevonden ! aanpassen voorkeur");
                                try {
                                    vanaf = parser.parse(vanafdatum);
                                } catch (Exception e) {
                                }
                                try {
                                    tot = parser.parse(totEnMetDatum);
                                } catch (Exception e) {
                                }
                                if (tot == null) {
                                    persoon.setRaakpunt(true);
                                }
                                break;
                            case "FAM":
                                System.out.println("Familiedag gevonden ! aanpassen voorkeur");
                                try {
                                    vanaf = parser.parse(vanafdatum);
                                } catch (Exception e) {
                                }
                                try {
                                    tot = parser.parse(totEnMetDatum);
                                } catch (Exception e) {
                                }
                                if (tot == null) {
                                    persoon.setFamiliedag(true);
                                }
                                break;
                            case "OUD":
                                System.out.println("Oudercomité gevonden ! aanpassen voorkeur");
                                try {
                                    vanaf = parser.parse(vanafdatum);
                                } catch (Exception e) {
                                }
                                try {
                                    tot = parser.parse(totEnMetDatum);
                                } catch (Exception e) {
                                }
                                if (tot == null) {
                                    persoon.setOudercomite(true);
                                }
                                break;
                            case "OVK":
                                System.out.println("Ontmoetingsdag gevonden ! aanpassen voorkeur");
                                try {
                                    vanaf = parser.parse(vanafdatum);
                                } catch (Exception e) {
                                }
                                try {
                                    tot = parser.parse(totEnMetDatum);
                                } catch (Exception e) {
                                }
                                if (tot == null) {
                                    persoon.setOntmoetingsdag(true);
                                }
                                break;
                            default:
                                System.out.println("Skipping import of " + mailcode);
                                break;
                        }

                        StringBuilder sb = new StringBuilder("Geïmporteerde Data:\n=====================\n")
                                .append("ADRESID : ").append(adresid).append("\n")
                                .append("MAILCODE : ").append(mailcode).append("\n")
                                .append("VANAF : ").append(vanafdatum).append("\n")
                                .append("TOT : ").append(totEnMetDatum).append("\n")
                                .append("UNIEKEID : ").append(uniekeid).append("\n")
                                .append("UPD : ").append(updateTijd).append("\n")
                                .append("SUPD : ").append(deleteTijd).append("\n")
                                .append("==========================");
                        counter++;
                        Notitie notitie1 =maakNotitie(sb.toString());
                        notitie1.setPersoon(persoon);
                        List<Notitie> notities = persoon.getNotities();
                        if(null == notities){
                            notities = new ArrayList<Notitie>();
                            notities.add(notitie1);
                            persoon.setNotities(notities);
                        }
                        else
                        {
                            persoon.getNotities().add(notitie1);
                        }
                        System.out.println(counter + "   Records verwerkt !" + sb.toString());
                        persistence.getEntityManager().merge(persoon);
                    } else {
                        System.out.println("Persoon niet gevonden dus skipping ... " + adresid);
                    }
                tx.commit();
            }
            catch(Exception e)
            {
                System.out.println("fout tijdens record");
                e.printStackTrace();
            }
            }catch(Exception e){e.printStackTrace();}
        }


    }

    @Override
    public void importAdresEL(File file) throws IOException {
        System.out.println("inside service file = "  + file.getAbsolutePath());
        System.out.println("inside service size = "  + file.getAbsoluteFile().length());
        Reader reader = Files.newBufferedReader(file.toPath(),Charset.forName("cp1252"));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        String[] nextRecord;
        int counter = 0;
        int found = 0;
        while ((nextRecord = csvReader.readNext()) != null) {
            try {
                String adresid = nextRecord[0];
                String userid = nextRecord[1];
                String sortstring = nextRecord[2];
                String uniekeid = nextRecord[3];
                String updateTijd = nextRecord[4];
                String deleteTijd = nextRecord[5];
                String rectype = nextRecord[6];
                Persoon persoon = null;
                Adres adres = null;
                try (Transaction tx = persistence.createTransaction()) {
                    try {
                        persoon = persistence.getEntityManager().createQuery("select e from kinderkankerfonds$Persoon e where e.uniekeid = '" + uniekeid + "'", Persoon.class).getSingleResult();
                        adres = persistence.getEntityManager().createQuery("select e from kinderkankerfonds$Adres e where e.uniekeid = '" + adresid + "'", Adres.class).getSingleResult();
                    }catch (Exception e){System.out.println("error :" + e.getMessage());}

             //       if(null != persoon && null != persoon.getVoornaam()&&  null != adres && null != adres.getStraatnaam())
           //         {
                        System.out.println("Persoon : " + persoon.getVoornaam() + " " + persoon.getVoornaam() + "  <> Adres : " + adres.getStraatnaam());
                        found++;
                        System.out.println(counter + "   Records verwerkt !");
              //      }
                        tx.commit();
                    }
                counter++;
                }
                catch(Exception e)
                {
                    System.out.println("fout tijdens record");
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void importKKFrela(File file) throws IOException {
        System.out.println("inside service file = "  + file.getAbsolutePath());
        System.out.println("inside service size = "  + file.getAbsoluteFile().length());
        Reader reader = Files.newBufferedReader(file.toPath(),Charset.forName("cp1252"));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        String[] nextRecord;
        int counter = 0;
        while ((nextRecord = csvReader.readNext()) != null) {
            try {
                String adresfrom = nextRecord[0];
                String adresid = nextRecord[1];
                String link = nextRecord[2];
                String linkid = nextRecord[3];
                String reltype = nextRecord[4];
                String hoofdlink = nextRecord[5];
                String uniekeid = nextRecord[6];
                String updateTijd = nextRecord[7];
                String deleteTijd = nextRecord[8];
                String idemid = nextRecord[9];
                Persoon persoon = null;
                Adres adres = null;
                try (Transaction tx = persistence.createTransaction()) {
                    persoon = findPersoonByAdresUniekeId(adresid);
                    // if(null == persoon ){ persoon = findPersoonByUniekeId(uniekeid);}
                    if(null != persoon ) {
                    //    System.out.println("Persoon : " + persoon.getVoornaam() + " " + persoon.getFamilienaam() + " uniekeid : " + uniekeid + " <> " + linkid );
                        RelatieType relatieType = null;
                        switch (reltype.trim()) {
                            case "1":
                                System.out.println("Ouders/Patiënten");
                                relatieType = getRelatieType("Ouders/Patiënten");
                                break;
                            case "2":
                                System.out.println("Broer");
                                relatieType = getRelatieType("Broer");
                                break;
                            case "3":
                                System.out.println("Moeder");
                                relatieType = getRelatieType("Moeder");
                                break;
                            case "4":
                                System.out.println("Zus");
                                relatieType = getRelatieType("Zus");
                                break;
                            case "5":
                                System.out.println("Tante");
                                relatieType = getRelatieType("Tante");
                                break;
                            case "6":
                                System.out.println("Oom");
                                relatieType = getRelatieType("Oom");
                                break;
                            case "7":
                                System.out.println("Neef");
                                relatieType = getRelatieType("Neef");
                                break;
                            case "8":
                                System.out.println("Nicht");
                                relatieType = getRelatieType("Nicht");
                                break;
                            case "9":
                                System.out.println("Zoon");
                                relatieType = getRelatieType("Zoon");
                                break;
                            case "10":
                                System.out.println("Dochter");
                                relatieType = getRelatieType("Dochter");
                                break;
                            case "11":
                                System.out.println("Andere");
                                relatieType = getRelatieType("Andere");
                                break;
                            case "12":
                                System.out.println("Familie");
                                relatieType = getRelatieType("Familie");
                                break;
                            case "013":
                                System.out.println("Halfzus");
                                relatieType = getRelatieType("Halfzus");
                                break;
                            case "014":
                                System.out.println("Halfbroer");
                                relatieType = getRelatieType("Halfbroer");
                                break;
                            default:
                                System.out.println("Skipping import of " + reltype);
                                relatieType = getRelatieType("Onbekend");
                                break;
                        }
                        Persoon linkPersoon = findPersoonByAdresUniekeId(linkid);
                        if(null != linkPersoon){
                            Relatie relatie = maakRelatie(relatieType);
                            try {
                                if (relatie.getPersoonOrigine() != persoon || relatie.getPersoonDoel() != linkPersoon) {
                                    relatie.setPersoonOrigine(persoon);
                                    relatie.setPersoonDoel(linkPersoon);
                                }
                            }catch(Exception e){System.out.println("geen relatie gevonden");}
                            List<Relatie> relaties = persoon.getRelaties();
                            if(null == relatie){
                                System.out.println("Relatie is null, aanmaken nieuwe");
                                relaties = new ArrayList<Relatie>();
                                relaties.add(relatie);
                                persoon.setRelaties(relaties);
                            }
                            else{
                                    persoon.getRelaties().add(relatie);
                            }
                        }
                    }
                    System.out.println(counter + "   Records verwerkt !");
                    tx.commit();
                }
                counter++;
            }
            catch(Exception e)
            {
                System.out.println("fout tijdens record");
                e.printStackTrace();
            }
        }
    }

    private Relatie maakRelatie(RelatieType relatieType) {
        Relatie relatie = null;
        try (Transaction tx = persistence.getTransaction()) {
            relatie = metadata.create(Relatie.class);
            relatie.setType(relatieType);
            persistence.getEntityManager().persist(relatie);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van relatie : " + e.getMessage());
            e.printStackTrace();
        }
        return relatie;
    }

    private RelatieType getRelatieType(String naam) {
        RelatieType relatieType = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<RelatieType> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$RelatieType e where e.naam = :naam", RelatieType.class);
            query.setParameter("naam", naam);
            try{
                relatieType = query.getSingleResult();
            }catch (NoResultException e){
                relatieType = metadata.create(RelatieType.class);
                relatieType.setNaam(naam);
                persistence.getEntityManager().persist(relatieType);
            }
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van relatieType : " + e.getMessage());
        }
        return relatieType;
    }

    @Override
    public void importTmpPat(File file) throws IOException {
       importCSV(file);
    }

    private ContactInfo maakContactInfo(String telVast, String telMob, String email, Persoon persoon) {
        ContactInfo contactInfo = null;
        try (Transaction tx = persistence.getTransaction()) {
            TypedQuery<ContactInfo> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$ContactInfo e where e.persoon.id = :id and (e.telefoon = :telefoon or e.gsm = :gsm or e.email = :email) ", ContactInfo.class);
            query.setParameter("telefoon", telVast);
            query.setParameter("gsm", telMob);
            query.setParameter("email", email);
            query.setParameter("id", persoon.getId());
            try {
                contactInfo = query.getFirstResult();
            }catch (Exception e){}
            if (contactInfo == null) {
                contactInfo = metadata.create(ContactInfo.class);
                contactInfo.setTelefoon(telVast);
                contactInfo.setGsm(telMob);
                contactInfo.setEmail(email);
                contactInfo.setActief(true);
                contactInfo.setPersoon(persoon);
                persistence.getEntityManager().persist(contactInfo);
            }
            else{
                contactInfo.setTelefoon(telVast);
                contactInfo.setGsm(telMob);
                contactInfo.setEmail(email);
                contactInfo.setActief(true);
                contactInfo.setPersoon(persoon);
            }

            contactInfo = persistence.getEntityManager().merge(contactInfo);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van categorie : " + e.getMessage());
        }
        return contactInfo;
    }


    private ContactInfo maakContactInfo(String telVast, String telMob, String email) {
        ContactInfo contactInfo = null;
        try (Transaction tx = persistence.getTransaction()) {
            contactInfo = metadata.create(ContactInfo.class);
            contactInfo.setTelefoon(telVast);
            contactInfo.setGsm(telMob);
            contactInfo.setEmail(email);
            contactInfo.setActief(true);
            persistence.getEntityManager().persist(contactInfo);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van categorie : " + e.getMessage());
            e.printStackTrace();
        }
        return contactInfo;
    }


    private Persoon maakPersoon(String uniekeid) {
            Persoon result = null;
        try (Transaction tx = persistence.createTransaction() )
             {
            TypedQuery<Persoon> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Persoon e where e.uniekeid = :uniekeid", Persoon.class);
            query.setParameter("uniekeid", uniekeid);
            try{
            result = query.getSingleResult();
            }catch(NoResultException nre){
                System.out.println("Persoon niet gevonden , aanmaken nieuwe ..");
            }
            catch (Exception e){ e.printStackTrace();}
            if (null == result) {
                result = metadata.create(Persoon.class);
                result.setUniekeid(uniekeid);
                persistence.getEntityManager().persist(result);
            }
            tx.commit();
            result = persistence.getEntityManager().reload(result,"persoon-browse-view");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Er is iets foutgelopen tijdens maken van persoon : " + e.getMessage());
        }
            return  result;
    }


    private Persoon maakPersoon() {
        Persoon result = null;
        try (Transaction tx = persistence.getTransaction()) {
            result = metadata.create(Persoon.class);
            persistence.getEntityManager().persist(result);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van persoon : " + e.getMessage());
        }
        return  result;
    }


    private Persoon findPersoonByUniekeId(String uniekeid) {
        Persoon result = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Persoon> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Persoon e where e.uniekeid = :uniekeid", Persoon.class);
            query.setParameter("uniekeid", uniekeid);
            try{
                result = query.getSingleResult();
            }catch (Exception e){}
            if (null == result) {
                System.out.println("Geen Persoon gevonden met id : " + uniekeid);
            } else {
                System.out.println("Gevonden pesoon met id " + uniekeid + result.getInstanceName());
                result = persistence.getEntityManager().reload(result,"persoon-browse-view");
            }
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens vinden van persoon : " + e.getMessage());
        }
        return  result;
    }

    private Persoon findPersoonByAdresUniekeId(String uniekeid) {
        Persoon result = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Adres> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Adres e where e.uniekeid = :uniekeid", Adres.class);
            query.setParameter("uniekeid", uniekeid);
            Adres adres = null;
            try{
                adres = query.getSingleResult();
            }catch (NoResultException e){System.out.println("Geen adres gevonden met id : " + uniekeid);}
            if (null != adres) {
                System.out.println("Gevonden adres met id " + uniekeid + " : " + adres.getInstanceName());
                try{
                    adres = persistence.getEntityManager().reload(adres,"adres-full-view");
                    //result = persistence.getEntityManager().reload(result,"persoon-browse-view");
                    result = adres.getPersoon();
                    if(null != result) {
                        result = persistence.getEntityManager().reload(result, "persoon-browse-view");
                        System.out.println("Persoon gevonden via adres met id " + uniekeid + " : " + result.getInstanceName());
                    }
                }catch (Exception e){e.printStackTrace();}
            }
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens vinden van persoon : " + e.getMessage());
        }
        return  result;
    }


    private Persoon findPersoonByVoorEnFamilienaam(String voornaam, String familienaam) {
        Persoon result = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Persoon> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Persoon e where e.familienaam = :fam",Persoon.class);
            query.setParameter("fam", familienaam + " " + voornaam);
            try{
                result = query.getSingleResult();
            }catch (Exception e){}
            if (null != result) {
                System.out.println("persoon gevonden met fam : " + familienaam + " " + voornaam);
                result = persistence.getEntityManager().reload(result,"persoon-browse-view");
            }
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens vinden van persoon : " + e.getMessage());
            e.printStackTrace();
        }
        return  result;
    }



    private Persoon findPersoonByFamilienaam(String familienaam) {
        Persoon result = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Persoon> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Persoon e where e.familienaam = :fam",Persoon.class);
            query.setParameter("fam", familienaam );
            try{
                result = query.getSingleResult();
            }catch (Exception e){}
            if (null != result) {
                System.out.println("persoon gevonden met fam : " + familienaam );
                result = persistence.getEntityManager().reload(result,"persoon-browse-view");
            }
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens vinden van persoon : " + e.getMessage());
            e.printStackTrace();
        }
        return  result;
    }



    private Persoon findPersoonByUniekeid(String uniekeid) {
        Persoon result = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Persoon> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Persoon e where e.uniekeid = :uniekeid",Persoon.class);
            query.setParameter("uniekeid", uniekeid);
            Persoon persoon = null;
            try{
                persoon = query.getSingleResult();
            }catch (Exception e){}
            if (null != persoon) {
                System.out.println("persoon gevonden met uniekeid : " + uniekeid);
                try{
                    result = persistence.getEntityManager().merge(persoon);
                    System.out.println("Persoon gevonden via naam " + result.getInstanceName());
                }catch (Exception e){}
            }
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens vinden van persoon : " + e.getMessage());
            e.printStackTrace();
        }
        return  result;
    }

    private Geslacht getGeslacht(String sex) {
        Geslacht geslacht = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Geslacht> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Geslacht e where e.naam = :naam", Geslacht.class);
            query.setParameter("naam", sex);
            try{
            geslacht = query.getSingleResult();
            }catch (Exception e){}
            if (geslacht == null) {
                geslacht = metadata.create(Geslacht.class);
                geslacht.setNaam(sex);
                persistence.getEntityManager().persist(geslacht);
            }
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van geslacht : " + e.getMessage());
        }
        return geslacht;
    }


    private Adres maakAdres(String straat, String postnummer, String stad, Persoon persoon, String uniekeid) {
        Adres adres = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Adres> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Adres e where e.persoon.id = :id and e.uniekeid = :uniekeid", Adres.class);
            query.setParameter("uniekeid", uniekeid)
                .setParameter("id", persoon.getId())
                    .setViewName("adres-full-view");
            try{
            adres = query.getSingleResult();
            }catch (Exception e){}
            if (adres == null) {
                adres = metadata.create(Adres.class);
                adres.setStraatnaam(straat);
                adres.setActief(true);
                adres.setPostcode(postnummer);
                adres.setStad(stad);
                adres.setPersoon(persoon);
                adres.setUniekeid(uniekeid);
                persistence.getEntityManager().persist(adres);
            }
            else
            {
                adres.setStraatnaam(straat);
                adres.setActief(true);
                adres.setPostcode(postnummer);
                adres.setStad(stad);
            }
            adres = persistence.getEntityManager().merge(adres);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van Adres : " + e.getMessage());
        }
        return adres;
    }
    private Adres maakAdres(String straat, String postnummer, String stad, String uniekeid) {
        Adres adres = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Adres> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Adres e where e.uniekeid = :uniekeid", Adres.class);
            query.setParameter("uniekeid", uniekeid);
            try{
                adres = query.getSingleResult();
            }catch (Exception e){System.out.println("Adres Niet gevonden , aanmaken nieuwe ...");}
            if (adres == null) {
                adres = metadata.create(Adres.class);
                adres.setStraatnaam(straat);
                adres.setActief(true);
                adres.setPostcode(postnummer);
                adres.setStad(stad);
                adres.setUniekeid(uniekeid);
                persistence.getEntityManager().persist(adres);
            }
            else
            {
                adres.setStraatnaam(straat);
                adres.setActief(true);
                adres.setPostcode(postnummer);
                adres.setStad(stad);
                adres = persistence.getEntityManager().merge(adres);
            }
            tx.commit();

            adres = persistence.getEntityManager().reload(adres,"adres-full-view");
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van Adres : " + e.getMessage());
            e.printStackTrace();
        }
        return adres;
    }
    private Adres maakAdres(String uniekeid) {
        Adres adres = null;
        try (Transaction tx = persistence.getTransaction()) {
            TypedQuery<Adres> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Adres e where e.uniekeid = :uniekeid", Adres.class);
            query.setParameter("uniekeid", uniekeid).setViewName("adres-full-view");
            try{
                adres = query.getSingleResult();
            }catch (Exception e){}
            if (adres == null) {
                adres = metadata.create(Adres.class);
                adres.setActief(true);
                adres.setUniekeid(uniekeid);
                persistence.getEntityManager().persist(adres);
            }
            else
            {
                adres.setActief(true);
            }
            adres = persistence.getEntityManager().merge(adres);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van Adres : " + e.getMessage());
        }
        return adres;
    }




    private Notitie maakNotitie(String notitieString) {
        Notitie notitie = null;
        try (Transaction tx = persistence.getTransaction()) {
            notitie = metadata.create(Notitie.class);
            notitie.setOmschrijving(notitieString);
            persistence.getEntityManager().persist(notitie);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van Notitie : " + e.getMessage());
        }
        return notitie;
    }


    private Notitie maakNotitie(String notitieString, Persoon persoon) {
        Notitie notitie = null;
        try (Transaction tx = persistence.getTransaction()) {
            TypedQuery<Notitie> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Notitie e where e.omschrijving = :omschrijving and e.persoon.id = :id", Notitie.class);
            query.setParameter("omschrijving", notitieString);
            query.setParameter("id", persoon.getId());
            try{
            notitie = query.getSingleResult();
            }catch (Exception e){}
            if (notitie == null) {
                notitie = metadata.create(Notitie.class);
                notitie.setOmschrijving(notitieString);
                notitie.setPersoon(persoon);
                persistence.getEntityManager().persist(notitie);
            }
            else {
                notitie.setOmschrijving(notitieString);
                notitie.setPersoon(persoon);
            }
            notitie = persistence.getEntityManager().merge(notitie);
            tx.commit();
       }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van Notitie : " + e.getMessage());
        }
        return notitie;
    }


    private Notitie maakNotitie(String notitieString, Adres adres) {
        Notitie notitie = null;
        adres = persistence.getEntityManager().find(Adres.class,adres.getId());
        try (Transaction tx = persistence.getTransaction()) {
            TypedQuery<Notitie> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Notitie e where e.omschrijving = :omschrijving and e.adressen.id = :id", Notitie.class);
            query.setParameter("omschrijving", notitieString);
            query.setParameter("id", adres.getId());
            try{
                notitie = query.getSingleResult();
            }catch (Exception e){e.printStackTrace();}
            if (notitie == null) {
                notitie = metadata.create(Notitie.class);
                notitie.setOmschrijving(notitieString);
                notitie.setAdressen(adres);
                persistence.getEntityManager().persist(notitie);
            }
            else
            {
                notitie.setOmschrijving(notitieString);
                notitie.setAdressen(adres);
            }
            notitie = persistence.getEntityManager().merge(notitie);
            tx.commit();
            System.out.println("Transaction Committed ! " +notitie.getOmschrijving());
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van Notitie : " + e.getMessage());
            e.printStackTrace();
        }
        return notitie;
    }

    private ContactInfo maakContactInfo(String telefoon, Persoon persoon) {
        ContactInfo contactInfo = null;
        try (Transaction tx = persistence.getTransaction()) {
            TypedQuery<ContactInfo> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$ContactInfo e where e.telefoon = :telefoon and e.persoon.id = :id", ContactInfo.class);
            query.setParameter("telefoon", telefoon);
            query.setParameter("id", persoon.getId());
            try{
            contactInfo = query.getSingleResult();
            }catch (Exception e){}
            if (contactInfo == null) {
                contactInfo = metadata.create(ContactInfo.class);
                contactInfo.setTelefoon(telefoon);
                contactInfo.setActief(true);
                contactInfo.setPersoon(persoon);
                persistence.getEntityManager().persist(contactInfo);
            }
            else
            {
                contactInfo.setTelefoon(telefoon);
                contactInfo.setActief(true);
                contactInfo.setPersoon(persoon);
            }
            contactInfo = persistence.getEntityManager().merge(contactInfo);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van contactinfo : " + e.getMessage());
        }
        return contactInfo;
    }


    private ContactInfo maakContactInfo(String telefoon) {
        ContactInfo contactInfo = null;
        try (Transaction tx = persistence.getTransaction()) {
            contactInfo = metadata.create(ContactInfo.class);
            contactInfo.setTelefoon(telefoon);
            contactInfo.setActief(true);
            persistence.getEntityManager().persist(contactInfo);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van contactinfo : " + e.getMessage());
            e.printStackTrace();
        }
        return contactInfo;
    }



    private Categorie maakCategorie(String categorie,Persoon persoon) {
        Categorie categorie1 = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Categorie> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Categorie e where e.naam = :naam", Categorie.class);
            query.setParameter("naam", categorie)
               //     .setParameter("id", persoon.getId())
                    .setViewName("categorie-view");
            try {
                categorie1 = query.getSingleResult();
            }catch (Exception e){}
            if (categorie1 == null) {
                categorie1 = metadata.create(Categorie.class);
                categorie1.setNaam(categorie);
                List<Persoon> personen = new ArrayList<Persoon>();
                personen.add(persoon);
                categorie1.setPersonen(personen);
                persistence.getEntityManager().persist(categorie1);
            }
            else
            {
                List<Persoon> personen = null;
                try {
                    personen = categorie1.getPersonen();
                }catch(NullPointerException npe)
                {
                    System.out.println("Geen gelinkte personen gevonden voor categorie : " + categorie);
                }
                if(null == personen)
                {
                    personen = new ArrayList<Persoon>();
                    categorie1.setPersonen(personen);
                }
                personen.add(persoon);
                categorie1.setPersonen(personen);
            }
            categorie1 = persistence.getEntityManager().merge(categorie1);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens maken van categorie : " + e.getMessage());
        }
        return categorie1;
    }

    private Categorie getCategorie(String naam) {
        Categorie categorie = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Categorie> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Categorie e where e.naam = :naam", Categorie.class);
            query.setParameter("naam", naam);
            try{
                categorie = query.getSingleResult();
            }catch (NoResultException nre){
                categorie = metadata.create(Categorie.class);
                categorie.setNaam(naam);
                persistence.getEntityManager().persist(categorie);
            }

            categorie = persistence.getEntityManager().reload(categorie,"categorie-view");
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("Er is iets foutgelopen tijdens opzoeken van categorie : " + e.getMessage());
            e.printStackTrace();
        }
        return categorie;
    }


}