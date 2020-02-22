Class  PassageSection

| method signature | responsibility | instance vars used | other class methods called | objects used with method calls | lines of code |
|:----------:|:--------------:|:------------------:|:--------------------------:|:------------------------------:|:-------------:|
| public PassageSection(int) | Set defaults | HashMap\<Integer, String> table StringBuilder desc | setUpTables() | StringBuilder.append() HashMap.get() | 2 |
| public String getDescription | Gets the description | StringBuilder desc | | StringBuilder.toString() | 1 |
| private void setUpTables | Sets up the PassageSection roll table | HashMap\<Integer, String> | | HashMap.put() | 20 | 