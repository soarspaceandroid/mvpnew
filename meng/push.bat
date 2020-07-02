Set /p input=Pls add comment :
git stash && git pull && git stash pop && git add . && git commit -m %input% && git push