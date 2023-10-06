FROM openjdk:17
LABEL maintainer = "ICABookingTeam"
WORKDIR /app
COPY ICA-Booking-App-0.0.1-SNAPSHOT.jar ICA-Booking-App.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "ICA-Booking-App.jar", "--spring.profiles.active=prod"]