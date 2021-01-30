for file in `find ./src/test/valid -type f -name '*.wacc'`
do
    > result.out
    ./grun antlr.Wacc program "$file" >> result.out
    if grep -q "mismatched" result.out
    then 
        echo "Failed $file"
    else
        echo "Success $file"
    fi
done
