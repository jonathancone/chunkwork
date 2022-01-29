Chunkwork
=========

__Simple Java utility for breaking tasks into smaller tasks__

Chunker helps you break down a large unit of work into smaller discrete serial units of work.  Take the following example:

    public void delete(final List<Integer> employeeIds) {
      Chunker.execute(employeeIds, 50, chunk -> {
          Session session = getSession();
          Query query = session.createSQLQuery("delete from Employee where employeeId in (:employeeIds)");
          query.setParameterList("employeeIds", chunk);
          query.executeUpdate();
        });
    }

In this circumstance, a database specific _IN clause limit exceeded_ error can be avoided because the SQL inputs are chunked into multiple blocks of 50.  Given a list of 130 employee IDs, the SQL statement above would be executed 3 times with distinct blocks of IDs (50 + 50 + 30).

In this example we print the numbers 1 - 64 to the terminal in a nice 8 x 8 grid:

    Chunker.execute(IntStream.range(0, 64)
                    .boxed()
                    .map(i -> String.format("%03x", i))
                    .toList(),
            8,
            System.out::println);

    // Output
    [000, 001, 002, 003, 004, 005, 006, 007]
    [008, 009, 00a, 00b, 00c, 00d, 00e, 00f]
    [010, 011, 012, 013, 014, 015, 016, 017]
    [018, 019, 01a, 01b, 01c, 01d, 01e, 01f]
    [020, 021, 022, 023, 024, 025, 026, 027]
    [028, 029, 02a, 02b, 02c, 02d, 02e, 02f]
    [030, 031, 032, 033, 034, 035, 036, 037]
    [038, 039, 03a, 03b, 03c, 03d, 03e, 03f]
