# -*- coding: utf-8 -*-
"""
Created on Sat Dec  9 15:54:16 2017

@author: Abhi
"""

# Declaring Lists
vertex_List = []
face_List = []

# input and output obj path
input_File = "/Users/Abhi/Desktop/CPL/Final proj/python/dodecahedron.obj"
output_File = "/Users/Abhi/Desktop/CPL/Final proj/python/output.obj"

file = open (input_File, "r")

# Getting the inputed factors from user
x_Value = input("Enter the value to scale x axis: ")

y_Value = input("Enter the value to scale y axis: ")

z_Value = input("Enter the value to scale z axis: ")

# Travesing the obj file and populate each lists.

with open(input_File) as file :
    for line in file:
        line = line.strip()
        if line.startswith("v"):
            vertex_List.append(line)

        if line.startswith("f"):
            face_List.append(line)


outputFile = open(output_File , "w")
outputFile.write('g Object001')

# Iterating vertices and scale it from the user inputted factors.

for v in vertex_List :
    splittedLine = v.split('  ')
    x = splittedLine[1]
    y = splittedLine[2]
    z = splittedLine[3]
    # Scalling values
    new_X = float(x) * float(x_Value)
    new_Y = float(y) * float(y_Value)
    new_Z = float(z) * float(z_Value)
 
    outputFile.write('\n')
    outputFile.write('v  ' + str(new_X) + '  ' + str(new_Y) + '  ' + str(new_Z))
    outputFile.write('\n')

for f in face_List :
    outputFile.write('\n')
    outputFile.write(f)


outputFile.close()
print("-----Program Excecuted Successfully-----")
print("Please open output.obj file")
