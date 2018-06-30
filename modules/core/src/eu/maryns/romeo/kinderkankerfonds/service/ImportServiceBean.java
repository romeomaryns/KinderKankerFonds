package eu.maryns.romeo.kinderkankerfonds.service;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.Metadata;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import eu.maryns.romeo.kinderkankerfonds.entity.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service(ImportService.NAME)
public class ImportServiceBean implements ImportService {

    private List<Persoon> personen = new ArrayList<Persoon>();

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
        try (Transaction tx = persistence.createTransaction()) {

            while ((nextRecord = csvReader.readNext()) != null) {
                try {
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
                    String uniekeid = nextRecord[35] ;
                    String updateTijd = nextRecord[37];
                    String deleteTijd = nextRecord[38];

                    Persoon persoon = maakPersoon(uniekeid);

                    persoon.setFamilienaam(naam);
                    persoon.setVoornaam(uniekeid);
                    if(sex.equals("1"))
                    {
                    //    persoon.setGeslacht(getGesclacht("Man"));
                    }
                    else
                    {
                   //     persoon.setGeslacht(getGesclacht("Vrouw"));
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
                        persoon.setDeleteTs(longParser.parse(deleteTijd));
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
                    List<Adres> adressen = new ArrayList<Adres>();
                    adressen.add(maakAdres(straat,postnummer,stad,persoon));
                    persoon.setAdressen(adressen,persoon);
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
                    List<Notitie> notities = new ArrayList<Notitie>();
                    notities.add(maakNotitie(sb.toString(),persoon));
                    persoon.setNotities(notities);
                    List<ContactInfo> contactInfo = new ArrayList<ContactInfo>();
                    contactInfo.add(maakContactInfo(telefoon,persoon));
                    persoon.setContactinfo(contactInfo);
                    personen.add(persoon);
                }
                catch(Exception e)
                {
                    System.out.println("fout tijdens record");
                    e.printStackTrace();
                }
            }
            System.out.println(personen.size());
            try (Transaction trans = persistence.createTransaction()) {
                for (Persoon persoon : personen) {
                    System.out.println(persoon.getInstanceName());
                    // for(Adres adres :persoon.getAdressen())
                    //  {
                    //      System.out.println(adres.getInstanceName());
                    //  }
                    //
                   persistence.getEntityManager().merge(persoon);

                }
                trans.commit();
            }

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
        while ((nextRecord = csvReader.readNext()) != null) {
            try {
                String recType = nextRecord[0];
                String dateOpen = nextRecord[1];
                String uniekeid = nextRecord[4];
                switch (recType){
                    case "0" :
                        nulRecords++;
                        Persoon persoon = findPersoonInList(personen,uniekeid);

                        System.out.println("RECTYPE : " + recType);
                        System.out.println("DATOPN : " + dateOpen);
                        System.out.println("DATAFSLUIT : " + nextRecord[2]);
                        System.out.println("GEOZONE : " + nextRecord[3]);
                        System.out.println("UNIEKID : " + uniekeid);
                        System.out.println("GEBDAT : " + nextRecord[5]);
                        System.out.println("AANSPR : " + nextRecord[6]);
                        System.out.println("NAAM : " + nextRecord[7]);
                        System.out.println("CTCPERS1 : " + nextRecord[8]);
                        System.out.println("CTCPERS2 : " + nextRecord[9]);
                        System.out.println("VOORNAAM : " + nextRecord[10]);
                        System.out.println("SEX : " + nextRecord[11]);
                        System.out.println("ADRES : " + nextRecord[12]);
                        System.out.println("WPL : " + nextRecord[13]);
                        System.out.println("POSTC : " + nextRecord[14]);
                        System.out.println("LAND : " + nextRecord[15]);
                        System.out.println("KKFMAIL : " + nextRecord[16]);
                        System.out.println("EMAIL : " + nextRecord[17]);
                        System.out.println("PREFEMAIL : " + nextRecord[18]);
                        System.out.println("ISPATIENT : " + nextRecord[19]);
                        System.out.println("ADRESOK : " + nextRecord[20]);
                        System.out.println("RIP : " + nextRecord[21]);
                        System.out.println("DATCREATIE : " + nextRecord[22]);
                        System.out.println("INFO : " + nextRecord[23]);
                        System.out.println("TELVAST : " + nextRecord[24]);
                        System.out.println("TELMOB : " + nextRecord[25]);
                        System.out.println("ADRESTYPE : " + nextRecord[26]);
                        System.out.println("UPDTIJD : " + nextRecord[27]);
                        System.out.println("SUPDTIJD : " + nextRecord[28]);
                        break;
                    case "1" :
                        eenRecords++;
                  /*      System.out.println("RECTYPE : " + recType);
                        System.out.println("DATOPN : " + dateOpen);
                        System.out.println("DATAFSLUIT : " + nextRecord[2]);
                        System.out.println("GEOZONE : " + nextRecord[3]);
                        System.out.println("UNIEKID : " + nextRecord[4]);
                        System.out.println("GEBDAT : " + nextRecord[5]);
                        System.out.println("AANSPR : " + nextRecord[6]);
                        System.out.println("NAAM : " + nextRecord[7]);
                        System.out.println("CTCPERS1 : " + nextRecord[8]);
                        System.out.println("CTCPERS2 : " + nextRecord[9]);
                        System.out.println("VOORNAAM : " + nextRecord[10]);
                        System.out.println("SEX : " + nextRecord[11]);
                        System.out.println("ADRES : " + nextRecord[12]);
                        System.out.println("WPL : " + nextRecord[13]);
                        System.out.println("POSTC : " + nextRecord[14]);
                        System.out.println("LAND : " + nextRecord[15]);
                        System.out.println("KKFMAIL : " + nextRecord[16]);
                        System.out.println("EMAIL : " + nextRecord[17]);
                        System.out.println("PREFEMAIL : " + nextRecord[18]);
                        System.out.println("ISPATIENT : " + nextRecord[19]);
                        System.out.println("ADRESOK : " + nextRecord[20]);
                        System.out.println("RIP : " + nextRecord[21]);
                        System.out.println("DATCREATIE : " + nextRecord[22]);
                        System.out.println("INFO : " + nextRecord[23]);
                        System.out.println("TELVAST : " + nextRecord[24]);
                        System.out.println("TELMOB : " + nextRecord[25]);
                        System.out.println("ADRESTYPE : " + nextRecord[26]);
                        System.out.println("UPDTIJD : " + nextRecord[27]);
                        System.out.println("SUPDTIJD : " + nextRecord[28]);*/
                        break;
                    default:    anderREcords++;break;
                }
                counter++;
                System.out.println("==========================");
            }
            catch(Exception e)
            {
                System.out.println("fout tijdens record");
                e.printStackTrace();
            }
        }
        System.out.println("Aantal records : " + counter + " waarvan :\n"+nulRecords + " nulRecords\n"+eenRecords+" eenRecords\n" +anderREcords + " andere records");

    }

    private Persoon findPersoonInList(List<Persoon> personen, String uniekeid) {
        Persoon returnVal = new Persoon();
        for (Persoon persoon : personen)
        {
            if(persoon.getVoornaam().equals(uniekeid))
            {
                System.out.println("Persoon gevonden !! " + persoon.getFamilienaam());
                returnVal = persoon;
            }
        }
        return  returnVal;
    }


    private Persoon maakPersoon(String uniekeid) {
            Persoon result = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Persoon> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Persoon e where e.voornaam = :voornaam", Persoon.class);
            query.setParameter("voornaam", uniekeid);
            List<Persoon> personen = query.getResultList();
            if (personen.size() == 0) {
                result = metadata.create(Persoon.class);
                result.setVoornaam(uniekeid);
                persistence.getEntityManager().persist(result);
            } else if (personen.size() == 1) {
                result = personen.get(0);
                result.setVoornaam(uniekeid);
            } else {
                throw new IllegalStateException("More than one persoon with uniekeid " + uniekeid);
            }
            tx.commit();
        }
            return  result;
    }

    private Geslacht getGesclacht(String sex) {
        Geslacht geslacht = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Geslacht> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Geslacht e where e.naam = :naam", Geslacht.class);
            query.setParameter("naam", sex);
            geslacht = query.getSingleResult();
            if (geslacht == null) {
                geslacht = metadata.create(Geslacht.class);
                geslacht.setNaam(sex);
                persistence.getEntityManager().persist(geslacht);
            }
            tx.commit();
        }
        return geslacht;
    }


    private Adres maakAdres(String straat, String postnummer, String stad,Persoon persoon) {
        Adres adres = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Adres> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Adres e where e.straatnaam = :straat and e.postcode = :postnummer and e.stad = :stad and e.persoon.id = :id", Adres.class);
            query.setParameter("straat", straat);
            query.setParameter("postnummer", postnummer);
            query.setParameter("stad", stad);
            query.setParameter("id", persoon.getId());
            adres = query.getFirstResult();
            if (adres == null) {
                adres = metadata.create(Adres.class);
                adres.setStraatnaam(straat);
                adres.setActief(true);
                adres.setPostcode(postnummer);
                adres.setStad(stad);
                adres.setPersoon(persoon);
                persistence.getEntityManager().persist(adres);
            }
            tx.commit();
        }
        return adres;
    }

    private Notitie maakNotitie(String notitieString, Persoon persoon) {
        Notitie notitie = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<Notitie> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$Notitie e where e.omschrijving = :omschrijving and e.persoon.id = :id", Notitie.class);
            query.setParameter("omschrijving", notitieString);
            query.setParameter("id", persoon.getId());
            notitie = query.getFirstResult();
            if (notitie == null) {
                notitie = metadata.create(Notitie.class);
                notitie.setOmschrijving(notitieString);
                notitie.setPersoon(persoon);
                persistence.getEntityManager().persist(notitie);
            }
            tx.commit();
       }
        return notitie;
    }

    private ContactInfo maakContactInfo(String telefoon, Persoon persoon) {
        ContactInfo contactInfo = null;
        try (Transaction tx = persistence.createTransaction()) {
            TypedQuery<ContactInfo> query = persistence.getEntityManager().createQuery(
                    "select e from kinderkankerfonds$ContactInfo e where e.telefoon = :telefoon and e.persoon.id = :id", ContactInfo.class);
            query.setParameter("telefoon", telefoon);
            query.setParameter("id", persoon.getId());
            contactInfo = query.getFirstResult();
            if (contactInfo == null) {
                contactInfo = metadata.create(ContactInfo.class);
                contactInfo.setTelefoon(telefoon);
                contactInfo.setPersoon(persoon);
                persistence.getEntityManager().persist(contactInfo);
            }
            tx.commit();
        }
        return contactInfo;
    }

}