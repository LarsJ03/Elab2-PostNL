import pandas as pd
import numpy as np

def calculate_distance(x1, y1, x2, y2):
    return np.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)

def find_closest_nodes():
    # Load the datasets with no headers
    nodes = pd.read_csv('Data/Raw/nodes.csv', delimiter=';', header=None)
    service_points = pd.read_csv('Data/Raw/ServicePointLocations.csv', delimiter=';', header=None)

    # Optionally, set column names for easier manipulation (comment out if unnecessary)
    nodes.columns = ['NODE ID', 'X', 'Y', 'SQUARE']
    service_points.columns = ['Location ID', 'X', 'Y', 'Square', 'Population', 'Total Deliveries', 'Total Pickups']

    # Prepare a column to store the closest node ID
    closest_nodes = []
    
    # Iterate over each service point
    for index, service_point in service_points.iterrows():
        # Get the service point coordinates
        sp_x = service_point['X']
        sp_y = service_point['Y']
        
        # Calculate the distance to each node
        distances = nodes.apply(lambda node: calculate_distance(sp_x, sp_y, node['X'], node['Y']), axis=1)
        
        # Find the index of the closest node
        closest_node_id = nodes.iloc[distances.idxmin()]['NODE ID']
        
        # Append the closest node ID to the list
        closest_nodes.append(closest_node_id)
    
    # Add the closest node IDs to the service points dataframe
    service_points[7] = closest_nodes  # Use integer index to add the new column

    # Save the updated dataframe to a new CSV file without headers
    service_points.to_csv('Data/OwnDataset/ServicePointLocationsUpdated.csv', index=False, sep=';', header=False)

# Run the function
find_closest_nodes()
