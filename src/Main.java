import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class Main {
    
    public static void main(String[] args) {

        LinkedList<People> list = ReadCSV("foreign_names/foreign_names.csv");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).Name);
        }

    }

    public static LinkedList<People> ReadCSV(String file) {

        String line = "";

        LinkedList<Division> division_list = new LinkedList<Division>();
        LinkedList<People> people_list = new LinkedList<People>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            // skip first line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                People p = new People();

                p.ID = Integer.parseInt(values[0]);
                p.Name = values[1];
                p.Gender = values[2];
                p.BirthDate = values[3];
                p.Division = null;
                p.Salary = Float.parseFloat(values[5]);

                // find division
                for (int i = 0; i < division_list.size(); i++) {
                    Division d = division_list.get(i);

                    if (d.Name == values[4]) {
                        p.Division = d;
                    }
                }

                // add if no division
                if (p.Division == null) {
                    p.Division = new Division(division_list.size() + 1, values[4]);
                }

                // add to the people_list
                people_list.add(p);
                
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return people_list;

    }
}
