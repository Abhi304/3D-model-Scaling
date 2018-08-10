#include <iostream>
#include <fstream>
#include <string>
#include <sstream>

using namespace std;

int main()
{

	//Opening the .obj file and output.obj file
	ifstream inFile;
	ofstream outFile;
	inFile.open("dodecahedron.obj");
	outFile.open("output.obj");

 if (inFile.fail())
 {
 	cerr << "Error opening file" << endl;
 	exit(1);
 }
 //Taking user inputted factor from user
 int a;
 int b;
 int c;
	 cout << "Enter the value to scale x axis: ";
	 cin  >> a ;
	 cout << "Enter the value to scale y axis: ";
	 cin  >> b ;
	 cout << "Enter the value to scale z axis: ";
	 cin  >> c ;

 string shape; 
 float X;
 float Y;
 float Z;
 	
 	//Adding the input file into variables	
 	 while(inFile >> shape >> X >>  Y >>  Z){
	  
 	 	
 		if(shape== "v")
		 {
		 X=X*a; 
		 Y=Y*b;
		 Z=Z*c;
		 }
		//Writing in the output file.
		outFile << shape<< " " << X <<" " << Y << " " << Z << endl;
 
 		}
 		cout << "------Program Executed Successfully.------"<< endl;
 		cout << "Please Open output.obj..";
 		inFile.close();
		outFile.close();
	

return 0;
}