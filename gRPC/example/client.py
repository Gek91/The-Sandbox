import grpc
import route_guide_pb2_grpc
import logging


def format_point(point):
    return "latitude: %d, longitude: %d" % (point.latitude, point.longitude)


def get_feature(stub, point):
    feature = stub.GetFeature(point)
    if not feature.location:
        print("Server returned incomplete feature")
        return

    if feature.name:
        print(
            "Feature called %r at %s"
            % (feature.name, format_point(feature.location))
        )
    else:
        print("Found no feature at %s" % format_point(feature.location))

def list_features(stub, rectagle):
    for feature in stub.ListFeatures(rectagle):
        print(
            "Feature called %r at %s"
            % (feature.name, format_point(feature.location))
        )

def record_route(stub, route):
    route_summary = stub.RecordRoute(route)
    print("Finished trip with %s points " % route_summary.point_count)
    print("Passed %s features " % route_summary.feature_count)
    print("Travelled %s meters " % route_summary.distance)
    print("It took %s seconds " % route_summary.elapsed_time)

def route_chat(stub, notes):
    for responses in stub.RouteChat(notes):
        for response in responses:
            print(
                "Received message %s at %s"
                % (response.message, format_point(response.location))
            )

def run():

    with grpc.insecure_channel("localhost:50051") as channel:
        stub = route_guide_pb2_grpc.RouteGuideStub(channel)

        point = None
        rectangle = None
        route = None
        notes = None

        print("-------------- GetFeature --------------")
        get_feature(stub, point)
        print("-------------- ListFeatures --------------")
        list_features(stub, rectangle)
        print("-------------- RecordRoute --------------")
        record_route(stub, route)
        print("-------------- RouteChat --------------")
        route_chat(stub, notes)


if __name__ == "__main__":
    logging.basicConfig()
    run()