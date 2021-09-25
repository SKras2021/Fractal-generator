# Fractal generator

DESCRIPTION
Original program designed to generate multi-colored images of fractal structures in square, triangle and round fields.

GENERATION ALGORITHM
1.Creating shape
2.Moving shape

Starting with a field 500 by 500 pixels
//Creating shape
1.A random untaken tile is picked.
2.A color is picked randomly (red, green blue).
3.The chosen tile is colored with the chosen color.
4.Color is picked again. If the newly chosen color differs from the previously chosen one, movement starts. (Generation of the shape is over).
5.If the chosen color is the same, then a random adjecent pixel is picked. If the chosen pixel is free, it is colored with the selected color.
6.Repeating steps 4 and 5 until the shape generation ends (either at step four because of the different color choice, or at step five because the tile is taken)
//Moving shape
1.Direction is picked randomly.
2.If the shape can not move because one or more of the tiles are taken, then the cycle is done. Otherwise the entire shape is moving 1 pixel in the said direction 
and these steps are reapeated until the shape collides with the border or with another shape.

HOW TO USE
In this project there are 3 .java files. CirclePixel - for round field, tringlePixel - for triangle field and pixelNEW1000 for default square field. Start with 
pixelNEW1000 to see the classic experience.
In each of these files there is java code which, upon compiling, launches the window of the program.
Upon fully loading, the program resolution will change to higher one and new elements of the interface will appear. On the top left there will be a field where
the fractal will be generated. On the right you will see a toolbox. There you have a button to make a screenshot of the fractal and a label showing how full
is the field in percents. You can use the upper text field to delete one color (red, green, blue). Use the lower text field to enter how many generation cycles 
to run, then press start.
I would recommend starting out with 10000 to 15000. Please be patient because the generation procces will take some time.
