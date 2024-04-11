FROM ubuntu:latest
LABEL authors="fruizotero"

ENTRYPOINT ["top", "-b"]