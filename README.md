chunkwork
=========

Idiomatic Java utilities for breaking tasks into smaller tasks.

One of the principal uses of the ChunkWorkTemplate is to help alleviate issues like SQL in-clause limits.  Take the following example:


    @Override
    public void delete(final List<Integer> employeeIds) {

      new ChunkWorkTemplate<Integer>(50, employeeIds) {
        @Override
        protected void executeOnChunk(List<Integer> chunk) {

          Session session = getSession();
          Query query = session.createSQLQuery("delete from Employee where employeeId in (:employeeIds)");
          query.setParameterList("employeeIds", chunk);
          query.executeUpdate();
        }
      }.execute();
    }

In this circumstance, database specific IN clause limit exceeded error messages can be avoided because the clause is broken into chunks.  In this case, each chunk is 50 items, so for a list of employee IDs that is 200 items long, the SQL would be executed 4 times, each time using a block of 50 distinct employee IDs.
