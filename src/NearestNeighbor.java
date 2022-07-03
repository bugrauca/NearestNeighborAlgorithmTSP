import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class NearestNeighbor {

	static double tourLength;

	public static void main(String[] args) throws FileNotFoundException {

		long startTime = System.currentTimeMillis(); // Timer starts.

		ArrayList<City> cityList = readFile();
		int numOfCities = cityList.size();
		double[][] adjacencyMatrix = adjacencyMatrix(cityList, numOfCities);
		int[] path = tspNearestNeighbor(numOfCities, adjacencyMatrix, cityList);

		// Store console print stream.
		PrintStream outputFile = System.out;
		// Name the output file.
		File file = new File("ShortestTour.txt");
		FileOutputStream fos = new FileOutputStream(file);
		// Create new print stream for file.
		PrintStream print = new PrintStream(fos);
		// Set file print stream.
		System.setOut(print);
		Tour(path, numOfCities);
		System.setOut(outputFile);

		System.out.println(
				"Tour & length via Nearest Neighbor Algorithm for " + numOfCities + " cities are shown in the " + file);
		long endTime = System.currentTimeMillis(); // Timer stops.
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in milliseconds: " + timeElapsed);

	}

	public static ArrayList<City> readFile() { // This method reads the file given below.

		ArrayList<City> cityList = new ArrayList<City>(); // Creates cityList ArrayList.

		try {

			File inputFile = new File("C:/Users/BUGRA/Desktop/ca4663.tsp"); // Path of the
																				// input file.
			Scanner reader = new Scanner(inputFile);
			String line = reader.nextLine(); // Reads first line.
			while (!line.equals("EOF")) {
				if (line.substring(0, 1).matches("[0-9]+")) { // Starts reading file from NODE_COORD_SECTION to EOF and
																// stores in cityList by creating City object.
					double cityId = Double.parseDouble(line.substring(line.indexOf(' '), line.lastIndexOf(' ')));
					double x = Double.parseDouble(line.substring(line.indexOf(' '), line.lastIndexOf(' ')));
					double y = Double.parseDouble(line.substring(line.lastIndexOf(' ')));
					City city = new City(cityId, x, y);
					cityList.add(city);
				}
				line = reader.nextLine();
			}
			reader.close(); // Closes the scanner method.
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cityList;
	}

	public static double[][] adjacencyMatrix(ArrayList<City> cityList, int numOfCities) { // This method calculates the
																							// distance between 2 cities
																							// and stores adjacent
																							// matrixes.
		double[][] adjacencyMatrix = new double[(numOfCities + 1)][(numOfCities + 1)];
		double distance = 0;

		for (int i = 0; i < numOfCities; i++) {
			for (int j = i; j < numOfCities; j++) {
				if (i == j) { // This means it's the same city.
					adjacencyMatrix[i][j] = 0;
				} else {
					distance = cityList.get(i).distanceBetween(cityList.get(j)); // Calculates distance between city i
																					// to city j
					adjacencyMatrix[i][j] = distance; // Stores adjacent matrix
					adjacencyMatrix[j][i] = distance; // Stores adjacent matrix
				}
			}
		}
		return adjacencyMatrix;
	}

	public static int[] tspNearestNeighbor(int numOfCities, double[][] adjacencyMatrix, ArrayList<City> cityList) {
		// This method constructs a path according to distances.

		int[] path = new int[numOfCities];
		path[0] = 0;
		int currentCity = 0, total = 0, nextCity = 0;
		double minDistance = Integer.MAX_VALUE;
		double distance = 0;
		cityList.get(0).setVisited(true);

		for (int k = 1; k < numOfCities; k++) {
			for (int j = 0; j < numOfCities; j++) {
				distance = adjacencyMatrix[currentCity][j];
				if (distance != 0 && distance < minDistance) {
					if (!cityList.get(j).isVisited()) {
						minDistance = distance;
						nextCity = j;
					}
				}
			}

			total += minDistance;
			path[k] = nextCity;
			cityList.get(nextCity).setVisited(true);
			currentCity = nextCity;
			minDistance = Integer.MAX_VALUE;

		}
		tourLength = (total + cityList.get(path[(numOfCities - 1)]).distanceBetween(cityList.get(0))); // Tour length calculation
		return path;

	}

	public static void Tour(int[] path, int numOfCities) { // This method contains the tour and it's length.
		System.out.println(tourLength);
		for (int i = 0; i < numOfCities; i++) {
			System.out.println(path[i]);
		}
	}
}
