
for fbreed in {3..18}
do
  mkdir outputs/${fbreed}
  for starve in {2..18}
  do
    for sbreed in `seq $(($starve+1)) 18`
    do
      java -jar wator-johan.jar -fBreed $fbreed -starve $starve -sBreed $sbreed -speed 0 -nbTurn $(( $((40*$fbreed)) + $((40*$starve)) + 800))
      gnuplot stats.conf
      mv stats.png outputs/${fbreed}/stats_${starve}_${sbreed}.png
    done
  done
done
