# Calculator

## Run backend
```bash
./gradlew bootWar
java -jar build/libs/calculator-1.0.0-SNAPSHOT.war
```

## Run frontend
### Via dist files
 ```bash
cd ui
npm install
npm run build
```
Open `project directory`/ui/dist/ui/index.html in browser

### Via development server
```bash
cd ui
npm install
npm run start
```
