echo 'running grunTester'

make
rm -f result.out
touch result.out

for FILE in `find ./src/test/valid -type f -name '*.wacc'` 
do
    ERROR=$(./grun antlr.Wacc program $FILE 2>&1 >/dev/null)
    if [ -z $ERROR]
    then 
        echo "Success $FILE"
    else
        echo "Failed $FILE"
        echo $FILE >> result.out 
        echo $ERROR "\n" >> result.out
    fi
done

[ -s result.out ] || echo "all test cases pass!" >> result.out



