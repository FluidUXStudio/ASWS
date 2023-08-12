class Eventsmodel {
  int? id;
  String? title;
  final List<int> time;
  final List<int> date;
  String? description;

  Eventsmodel(
      {this.id,
      this.title,
      required this.time,
      required this.date,
      this.description});

  factory Eventsmodel.fromJson(Map<String, dynamic> json) {
    return Eventsmodel(
      id: json['id'],
      title: json['title'],
      time: List<int>.from(json['time']),
      date: List<int>.from(json['date']),
      description: json['description'],
    );
  }

 Map<String, dynamic> toJson() {
    return {
      'id': id,
      'title': title,
      'description': description,
      'time': time,
      'date': date,
    };
}
}