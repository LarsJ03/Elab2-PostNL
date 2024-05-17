import pandas as pd

# Try to read the CSV file, skipping bad lines and warning about them
df = pd.read_csv('Data/Raw/edges.csv', on_bad_lines='skip', delimiter=';')

# Check the initial data to understand its structure
print("Initial data preview:")
print(df.head())

# Assuming the sixth column can be accessed directly if data has enough columns
if df.shape[1] >= 6:
    # Group by the sixth column, assuming the sixth column's index is 5 (0-based index)
    grouped_df = df.groupby(df.columns[5]).size()
    print("\nGrouped Data by the 6th column:")
    print(grouped_df)
else:
    print("Data does not contain six columns to group by.")