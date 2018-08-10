import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThreeD_Model {
	private static final String path = System.getProperty("user.dir");

	public static void main(String[] args) throws IOException {

		// Path to read the file.
		System.out.println("Reading Wavefront.obj file..");
		String fileName = path + "/src/" + "dodecahedron.obj";

		// One line at a time
		String line = null;
		BufferedReader bufferedReader = null;
		Scanner scanner = null;
		List<Axes> v_List = new ArrayList<Axes>();
		List<Axes> f_List = new ArrayList<Axes>();

		try {
			
			// Scanner class to get the inputed factors from user.
			scanner = new Scanner(System.in);
			System.out.print("Enter the value to scale x axis: ");
			double x1 = scanner.nextDouble();
			System.out.print("Enter the value to scale y axis: ");
			double y1 = scanner.nextDouble();
			System.out.print("Enter the value to scale z axis: ");
			double z1 = scanner.nextDouble();
			
			
			// To read text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// wrapping FileReader in BufferedReader.
		
			bufferedReader = new BufferedReader(fileReader);

			while (bufferedReader.ready()) {
				line = bufferedReader.readLine().trim();
				if (line.startsWith("v")) {
					Axes c = addVertices(line, x1, y1, z1);
					v_List.add(c);
				}
				if (line.startsWith("f")) {
					Axes f = addFaces(line);
					f_List.add(f);
				}
			}
			
			createObjOutFile(v_List, f_List);

		} catch (FileNotFoundException ex) {
			System.out.println("Error opening file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		} finally {
			bufferedReader.close();
			scanner.close();
		}
		System.out.println("-----Program Executed successfully----- ");
		System.out.println("Please open output.obj file");
	}
	
	/**
	 * This method creates an output obj file.
	 */
	private static void createObjOutFile(List<Axes> vertexList, List<Axes> faceList) throws IOException {
		BufferedWriter bufferedWriter = null;
		try {
			File inFile = new File(path + "/src/" + "output.obj");
			Writer writer = new FileWriter(inFile);
			bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write("g Object001");
			bufferedWriter.newLine();
			for (Axes c : vertexList) {
				String line = "v  " + c.getX() + "  " + c.getY() + "  " + c.getZ();
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
			bufferedWriter.newLine();

			for (Axes c : faceList) {
				String line = "f  " + (int) c.getX() + "  " + (int) c.getY() + "  " + (int) c.getZ();
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bufferedWriter.close();
		}

	}

	
	/**
	 * This method splits the line in order to store x,y,z Axes.
	 * and populates the Axes object.
	 * @param line
	 * @return
	 */
	private static Axes addVertices(String line, double x1, double y1, double z1) {

		String[] str = line.split("  ");
		double x = Double.parseDouble(str[1]) * x1 ;
		double y = Double.parseDouble(str[2]) * y1;
		double z = Double.parseDouble(str[3]) * z1;

		return new Axes(x, y, z);
	}

	/**
	 * To split the line in order to store x,y,z.
	 * @param line
	 * @return
	 */
	private static Axes addFaces(String line) {

		String[] str = line.split("  ");
		double x = Double.parseDouble(str[1]) ;
		double y = Double.parseDouble(str[2]);
		double z = Double.parseDouble(str[3]);

		return new Axes(x, y, z);
	}


}



