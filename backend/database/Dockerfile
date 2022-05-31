FROM mcr.microsoft.com/mssql/server 

LABEL MAINTAINER="zmanyath@student.wethinkcode.co.za"

# Create work directory
RUN mkdir -p /usr/work
WORKDIR /usr/work

# Copy all scripts into working directory
COPY . /usr/work/

# Switch to root user
USER root

# Grant permissions for the import-data script to be executable
RUN chmod +x /usr/work/import-data.sh

# Switch to default user
USER ${uid}:${gid}

EXPOSE 1433

CMD /bin/bash ./entrypoint.sh