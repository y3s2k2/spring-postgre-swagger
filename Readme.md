## Start server

```bash
./gradlew bootRun
```

ドキュメントは[swagger](http://localhost:8080/swagger-ui/index.html)で作成されます。

## Database

```bash
psql -h db -U postgres -d postgres
```

### Flyway の設定とマイグレーションの作成

参考：

```text
src/main/resources/db/migration
├── data
│   ├── common
│   │   └── V20240825.150000__add_people_data.sql
│   ├── development
│   │   └── V20240825.180000__add_people_data.sql
│   └── production
│       └── V20240825.210000__add_people_data.sql
└── ddl
    ├── V20240825.120000__create_person_table.sql
    └── V20240827.150000__create_book_table.sql
```
