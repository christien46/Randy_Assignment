package sample;

import java.io.*;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.util.Observable;

/**
 * Created by christien on 08/03/16.
 */
public class DataSource {

    /*
     *Created public treeMap
     */

    public static TreeMap<String, Integer>ham = new TreeMap<>();
    public static TreeMap<String, Integer> ham2 = new TreeMap<>();
    public static TreeMap<String, Integer> globalHam = new TreeMap<>();
    public static TreeMap<String, Integer> spam = new TreeMap<>();
    public static TreeMap<String, Double> trainSpamFreq = new TreeMap<>();
    public static TreeMap<String, Double> trainHamFreq = new TreeMap<>();
    public static TreeMap<String, Double> totalWOrd = new TreeMap<>();

    /*
     *Determines whether the selected object is a word or not
     */
    private static boolean isWord(String str) {
        String pattern = "^[a-zA-Z]*$";
        if (str.matches(pattern)) {
            return true;
        }
        return false;
    }

    //File[] filesInFolder = folder.listFiles();

    /*
     *This Function takes a file called folder and uses it to read through each folder and then using its position within
     *  the folder give information to a function in order to create a treemap for the folder containing the files.
     *  Within this function the probabilties of ham and spam are also calculated.
     */

    public static  ObservableList<TestFile> getAllNames(File folder) throws FileNotFoundException {

        ObservableList<TestFile> files = FXCollections.observableArrayList();
        System.out.println(folder);

        if (folder.isDirectory()) { //data
            //System.out.println("Pass");
            File[] FoldersInData = folder.listFiles(); //size 2
            File[] FilesTest = null;
            File[] FilesTrain = null;
            for (int i = 0; i < FoldersInData.length; i++) {
                //System.out.println(sizeOfData[i]);
                FilesTest = FoldersInData[0].listFiles();
                FilesTrain = FoldersInData[1].listFiles();
            }

            for (int z = 0; z < FilesTrain.length; z++) {
                //System.out.println("Pass");
                System.out.println(FilesTrain[z]);

            }
            spam = trainFile(FilesTrain[0]);
            ham = trainFile(FilesTrain[1]);
            ham2 = trainFile(FilesTrain[2]);

            //System.out.println(directoriesInFolder[0]);
        }

        double spamcounter = 500.0;
        double hamcounter = 2500.0;
        double ham2counter = 250.0;



        for (Map.Entry<String, Integer> entry : spam.entrySet()) {
            String Wordfinal = entry.getKey();
            Integer value = entry.getValue();

            // System.out.println(Wordfinal + " => " + value);

            double newVal = ((value * 1.0) / spamcounter);

            trainSpamFreq.put(Wordfinal, newVal);

        }


        for (Map.Entry<String, Integer> entry : ham2.entrySet()) {
            String Wordfinal = entry.getKey();
            Integer value = entry.getValue();

            // System.out.println(Wordfinal + " => " + value);

            double newVal = (value * 1.0) / spamcounter;

            trainHamFreq.put(Wordfinal, newVal);

        }


        for (Map.Entry<String, Double> entry : trainSpamFreq.entrySet()){

            String WordFinal = entry.getKey();
            Double FinalValue = entry.getValue();




        }
        return files;
    }





    /*
     *This function takes a file called train which holds a reference to a folder containing multiple files.This
     * functions then takes a word and the amount of times it appears and enters it into a treemap. The treemap is then
     * returned at the end of the function.This function is in charge of calculating the spamTreeMap
     */
    public static TreeMap<String,Integer> trainFile(File train) throws FileNotFoundException{

        TreeMap<String, Integer> trainFreq = new TreeMap<>();

        File[] filesInFolder = train.listFiles();
        for (int i = 0; i < filesInFolder.length; i++) {
            TreeMap<String,Integer> tempMap = new TreeMap<>();




            Scanner inputStream = new Scanner(filesInFolder[i]);
            while (inputStream.hasNext()) {

                String data = inputStream.next().toLowerCase();

                if (isWord(data) && data.length() > 1) {
                    if(!tempMap.containsKey(data)){
                        tempMap.put(data,1);
                        trainFreq.put(data,1);
                    }
                }
            }
            inputStream.close();


        }

        return trainFreq;
    }

    /*
     *This function takes a file called train which holds a reference to a folder containing multiple files.This
     * functions then takes a word and the amount of times it appears and enters it into a treemap. The treemap is then
     * returned at the end of the function.This function is in charge of calculating the HamTreeMap
     */


    public static TreeMap<String,Integer> trainFileHam(File train) throws FileNotFoundException{

        TreeMap<String, Integer> trainFreq = new TreeMap<>();

        File[] filesInFolder = train.listFiles();
        for (int i = 0; i < filesInFolder.length; i++) {
            TreeMap<String,Integer> tempMap = new TreeMap<>();




            Scanner inputStream = new Scanner(filesInFolder[i]);
            while (inputStream.hasNext()) {

                String data = inputStream.next().toLowerCase();

                if (isWord(data) && data.length() > 1) {
                    if(!tempMap.containsKey(data)){
                        tempMap.put(data,1);
                        globalHam.put(data,1);
                    }
                }
            }
            inputStream.close();


        }

        return globalHam;
    }

    /*public static TreeMap<String,double> process(TreeMap<String,Integer> SampleMap, String word){







    } */

}


//Other Code

                    /*if (trainFreq.containsKey(data)) {
                        int oldCount = trainFreq.get(data);
                        trainFreq.put(data, oldCount + 1);
                    } else {
                        trainFreq.put(data, 1);
                    } */


                      /*for(Map.Entry<String,Integer> entry : ham2.entrySet()) {
                         String key = entry.getKey();
                         Integer value = entry.getValue();

                         System.out.println(key + " => " + value);
                        } */

/*

 */