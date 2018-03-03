# PriceFile

  Program allows user to check whether car spare part has a price in price file. Price file is a .CSV file with the newest parts prices. First seven characters in every row represents FINIS (car part number), than after coma two characters represent shortcut of market name, for example "AX" means "Austria".  Price file contains undefined number of markets.
  User is asked to type a FINIS number and a path to price file (together with file name) in indicated Text-Field windows. Program checks every row for combination of finis + market shortcut and if such combination appears program highlight market name on green. If finis do not have price in some market then the market is highlighted on red. Program checks price for 21 markets only. 
