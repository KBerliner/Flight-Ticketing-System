# Flight and Passenger Tracking API

The Flight and Passenger Tracking API is a Test Driven-Development (TDD) project that I'm creating to learn Java and Spring Boot. This system will track flight and passenger information as well as generating boarding passes and checking into flights.

## In-Memory Database

The in-memory database is currently used as a placeholder for testing and development.

### Database Class

#### Constructor

Database(String name)

#### Properties

- String name
- HashMap<String, Table> tables
- ZonedDateTime createdAt

#### Methods

- String getName()
- HashMap<String, Table> getTables()
- Table createTable(String tableName)
- void dropTable(String tableName)
- ZonedDateTime getCreatedAt()

### Table Class

#### Constructor

Table(String name)

#### Properties

- String name
- HashMap<String, Row> rows
- ZonedDateTime createdAt

#### Methods

- String getName()
- HashMap<String, Row> getRows()
- ZonedDateTime getCreatedAt()
- void insertEntry(String rowId, HashMap<String, String> columnsMap)
- Row readEntry(String rowId)
- void updateEntry(String rowId, HashMap<String, String> valuesMap)
- void deleteEntry(String rowId)

### Row Class

#### Constructor

Row(String rowId, HashMap<String, String> columnValuesMap)

#### Properties

- String rowId
- HashMap<String, String> columnValuesMap
- ZonedDateTime createdAt
- ZonedDateTime updatedAt

#### Methods

- String getRowId()
- ZonedDateTime getCreatedAt()
- ZonedDateTime getUpdatedAt()
- HashMap<String, String> getColumnValuesMap()
- void setUpdatedAt(ZonedDateTime newUpdatedAt)

## Classes

### User

#### Constructor

User(String username, String firstName, String lastName, String email, String password) throws DataFormat Exception

#### Properties

- Boolean active
- String username
- String firstName
- String lastName
- String email
- String password
- Integer miles

#### Methods

- String getUsername()
- Boolean getActiveStatus()
- String getFirstName()
- String getLastName()
- String getEmail()
- String Password()
- Boolean comparePassword(String password)
- Integer getMiles()
- HashMap<String, String> getUserAsHashMap()

### Flight

#### Constructor

Flight(Double distance, ZonedDateTime departure, Duration duration)

#### Properties

- enum STATUS
  - UPCOMING
  - EN_ROUTE
  - LANDED

- Double distance
- ZonedDateTime departure
- Duration duration

#### Methods

- Double getDistance()
- STATUS getStatus()
- ZonedDateTime getDeparture()
- Duration getDuration()
- ZonedDateTime getArrival()

## Thanks

Thank you for reading and taking a look at my project! I'm always open to any suggestions, so if you see a way to improve my code, please let me know.
