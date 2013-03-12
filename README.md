chunkwork
=========

__Idiomatic Java utilities for breaking tasks into smaller tasks.__

One of the principal uses of the ChunkWorkTemplate is the circumstance where work needs to be broken into discrete pieces, but it does not need to be executed in parallel.  Take the following example:


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

In this circumstance, a database specific _IN clause limit exceeded_ error can be avoided because the SQL inputs are chunked into multiple blocks of 50.  Given a list of 130 employee IDs, the SQL statement above would be executed 3 times with distinct blocks of IDs (50 + 50 + 30).
